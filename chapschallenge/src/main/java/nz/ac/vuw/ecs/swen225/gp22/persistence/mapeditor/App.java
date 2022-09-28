package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids.TileGrass;

public final class App {
    public static void main(String[] args) {
        App app = new App();
        SwingUtilities.invokeLater(() -> app.createEditor());
    }

    private LevelEditor createEditor() {
        Tile[][] tiles = new Tile[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j] = new TileGrass();
            }
        }
        LevelEditor editor = new LevelEditor(new Level(tiles, new ArrayList<Entity>(), "Untitled", 100));
        editor.initialiseGUI();
        editor.setVisible(true);
        editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editor.pack();
        return editor;
    }
}
