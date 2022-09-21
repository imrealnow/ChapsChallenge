package com.github.imrealnow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

public class LevelEditor extends JFrame {
    private Level level;
    private File currentFile;
    private TileGrid tileGrid;

    LevelEditor(Level level) {
        assert SwingUtilities.isEventDispatchThread() : "Must be called from EDT";
        this.level = level;
        this.currentFile = null;
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
        tileGrid = level.map();
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

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        return menuBar;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem() {
            {
                setText("Save Level");
                addActionListener(e -> {
                    saveLevel();
                });
            }
        });
        fileMenu.add(new JMenuItem() {
            {
                setText("Load Level");
                addActionListener(e -> {
                    loadLevel();
                });
            }
        });
        return fileMenu;
    }

    private File showFileChooser(String title) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileFilter xmlFilter = new FileFilter() {
            @Override
            public boolean accept(java.io.File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
            }

            @Override
            public String getDescription() {
                return "XML files";
            }
        };
        fileChooser.setFileFilter(xmlFilter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    private void saveLevel() {
        String path;
        if (currentFile != null)
            path = currentFile.getAbsolutePath();
        else
            path = showFileChooser("Save Level").getAbsolutePath();
        if (path != null) {
            level = new Level(tileGrid.toString(), level.name(), level.description());
            LevelXMLFactory factory = new LevelXMLFactory(level);
            try {
                currentFile = factory.toXML(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLevel() {
        File levelFile = showFileChooser("Load level");
        if (levelFile != null) {
            LevelXMLFactory factory = new LevelXMLFactory(null);
            try {
                level = factory.fromXML(levelFile);
                currentFile = levelFile;
                initialiseGUI();
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
