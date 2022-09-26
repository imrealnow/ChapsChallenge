package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import javax.swing.JMenuBar;

public class EditorMenuBar extends JMenuBar {
    public EditorMenuBar(LevelEditor levelEditor) {
        add(new FileMenu(levelEditor));
    }
}
