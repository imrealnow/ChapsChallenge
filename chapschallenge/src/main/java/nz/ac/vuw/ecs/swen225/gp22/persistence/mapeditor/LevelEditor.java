package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids.TileGrass;

public class LevelEditor extends JFrame {
    private Level level;
    private TileGrid tileGrid;
    private LevelToolbar toolbar;
    private String levelName;
    private int levelTime;

    LevelEditor(Level level) {
        super("Chap's Challenge Map Editor");
        assert SwingUtilities.isEventDispatchThread() : "Must be called from EDT";
        this.level = level;
        this.levelName = level.getTitle();
        this.levelTime = level.getTimeLimit();
        setJMenuBar(new EditorMenuBar(this));
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
        tileGrid.setEntities(level.getEntities());
        toolbar.updateToolbar();
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public void setLevelTime(int levelTime) {
        this.levelTime = levelTime;
    }

    public void initialiseGUI() {
        // tile grid
        setSize(new Dimension(800, 600));
        setBackground(Color.lightGray);
        setMinimumSize(getSize());
        tileGrid = new TileGrid(level.getTiles());
        tileGrid.setEntities(level.getEntities());
        tileGrid.setMinimumSize(new Dimension(600, 600));
        getContentPane().setBackground(Color.lightGray);
        getContentPane().add(tileGrid);
        toolbar = new LevelToolbar(tileGrid, this);
        add(toolbar, BorderLayout.WEST);
    }

    public void onToolChanged(LevelToolbar.Tool tool) {
        tileGrid.setTool(tool);
    }

    public void updateLevel() {
        level = new Level(tileGrid.getTiles(), tileGrid.getEntities(), levelName, levelTime);
        toolbar.updateToolbar();
    }

    public void updateTileGrid(Tile[][] tiles) {
        if (tileGrid != null) {
            tileGrid.setTiles(tiles);
        } else {
            tileGrid = new TileGrid(tiles);
            tileGrid.setPreferredSize(new Dimension(600, 600));
            add(tileGrid, BorderLayout.WEST);
        }
    }

    public void resizeGrid(int width, int height) {
        System.out.println("Resizing to " + width + "x" + height);
        Tile[][] tiles = tileGrid.getTiles();
        Tile[][] newTiles = new Tile[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i < tiles.length && j < tiles[0].length) {
                    newTiles[i][j] = tiles[i][j];
                } else {
                    newTiles[i][j] = new TileGrass();
                }
            }
        }
        updateTileGrid(newTiles);
    }
}
