package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.persistence.LevelFactory;
import nz.ac.vuw.ecs.swen225.gp22.persistence.XMLSerializer;

public class LevelEditor extends JFrame {
    private Level level;
    private TileGrid tileGrid;

    LevelEditor(Level level) {
        assert SwingUtilities.isEventDispatchThread() : "Must be called from EDT";
        this.level = level;
        setJMenuBar(createMenuBar());
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    public void initialiseGUI() {
        // tile grid
        setSize(getPreferredSize());
        setMinimumSize(getSize());
        tileGrid = new TileGrid(level.getTiles());
        tileGrid.setPreferredSize(new Dimension(600, 600));
        add(tileGrid, BorderLayout.WEST);
        // tile palette
        TilePalette tilePalette = new TilePalette();
        tilePalette.setSize(
                new Dimension(getPreferredSize().width - tileGrid.getPreferredSize().width, getPreferredSize().height));
        tilePalette.setMinimumSize(tilePalette.getSize());
        MouseListener mouseListener = new MouseListener(tileGrid, tilePalette);
        tileGrid.addMouseListener(mouseListener);
        add(tilePalette);
    }

    private void updateTileGrid() {
        if (tileGrid != null) {
            tileGrid.setTiles(level.getTiles());
        } else {
            tileGrid = new TileGrid(level.getTiles());
            tileGrid.setPreferredSize(new Dimension(600, 600));
            add(tileGrid, BorderLayout.WEST);
        }
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        return menuBar;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        XMLSerializer serializer = new XMLSerializer();
        fileMenu.add(new JMenuItem() {
            {
                setText("Save Level");
                addActionListener(e -> {
                    level = new Level(tileGrid.getTiles(), level.getEntities(), level.getTitle(), level.getTimeLimit());
                    serializer.saveObjectToXML(this, "Save current level", new LevelFactory(), level);
                });
            }
        });
        fileMenu.add(new JMenuItem() {
            {
                setText("Load Level");
                addActionListener(e -> {
                    Level loadedLevel = serializer.loadObjectFromXML(this, "Load level", new LevelFactory());
                    if (loadedLevel != null) {
                        level = loadedLevel;
                        updateTileGrid();
                    }
                });
            }
        });
        return fileMenu;
    }
}
