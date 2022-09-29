package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids.TileGrass;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

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
        List<Entity> entities = new ArrayList<>();
        entities.add(new Player(new Vector(5, 5)));
        LevelEditor editor = new LevelEditor(new Level(-1, tiles, entities, "Untitled", 100));
        editor.initialiseGUI();
        editor.setVisible(true);
        editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editor.pack();
        return editor;
    }
}
