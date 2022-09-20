package com.github.imrealnow;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public final class App {
    public static void main(String[] args) {
        App app = new App();
        SwingUtilities.invokeLater(() -> app.createEditor());
    }

    private LevelEditor createEditor() {
        LevelEditor editor = new LevelEditor(new Level(10, 10, "untitled-level", ""));
        editor.initialiseGUI();
        editor.setVisible(true);
        editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editor.pack();
        return editor;
    }
}
