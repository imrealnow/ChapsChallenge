package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;

public class LevelEditor extends JFrame {
    private Level level;
    private TileGrid tileGrid;

    LevelEditor(Level level) {
        super("Chap's Challenge Map Editor");
        assert SwingUtilities.isEventDispatchThread() : "Must be called from EDT";
        this.level = level;
        setJMenuBar(new EditorMenuBar(this));
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void initialiseGUI() {
        // tile grid
        setSize(new Dimension(800, 600));
        setBackground(Color.lightGray);
        setMinimumSize(getSize());
        tileGrid = new TileGrid(level.getTiles());
        tileGrid.setMinimumSize(new Dimension(600, 600));
        getContentPane().setBackground(Color.lightGray);
        getContentPane().add(tileGrid);
        add(new LevelToolbar(tileGrid, this), BorderLayout.WEST);
    }

    public void updateLevel() {
        level = new Level(tileGrid.getTiles(), level.getEntities(), level.getTitle(), level.getTimeLimit());
    }

    public void updateTileGrid() {
        if (tileGrid != null) {
            tileGrid.setTiles(level.getTiles());
        } else {
            tileGrid = new TileGrid(level.getTiles());
            tileGrid.setPreferredSize(new Dimension(600, 600));
            add(tileGrid, BorderLayout.WEST);
        }
    }
}
