package com.github.imrealnow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class LevelEditor extends JFrame {
    private Level level;

    LevelEditor(Level level) {
        assert SwingUtilities.isEventDispatchThread() : "Must be called from EDT";
        this.level = level;
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
        TileGrid tileGrid = level.map();
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
}
