package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import nz.ac.vuw.ecs.swen225.gp22.persistence.LevelFactory;
import nz.ac.vuw.ecs.swen225.gp22.persistence.XMLSerializer;

/**
 * File menu for the menu bar of the level editor
 * 
 * @author Liam Green - greenliam
 */
public class FileMenu extends JMenu {
    private final LevelFactory levelFactory = new LevelFactory();
    private final XMLSerializer xmlSerializer = new XMLSerializer();
    private LevelEditor levelEditor;

    public FileMenu(LevelEditor levelEditor) {
        super("File");
        this.levelEditor = levelEditor;
        add(createSaveMenuItem());
        add(createLoadMenuItem());
    }

    private JMenuItem createSaveMenuItem() {
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(e -> {
            levelEditor.updateLevel();
            xmlSerializer.saveObjectToXML(this, "Save current level", new LevelFactory(), levelEditor.getLevel());
        });
        return saveMenuItem;
    }

    private JMenuItem createLoadMenuItem() {
        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.addActionListener(e -> {
            levelEditor.setLevel(xmlSerializer.loadObjectFromXML(this, "Load level", levelFactory));
            levelEditor.updateTileGrid();
        });
        return loadMenuItem;
    }
}
