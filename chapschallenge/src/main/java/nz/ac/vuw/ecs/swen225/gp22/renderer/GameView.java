package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Graphics;

import java.util.List;

import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

public class GameView extends JPanel {

    int imgSize = 32;

    Level level;

    public GameView(Level level) {
        this.level = level;
    }

    @Override
    public void paintComponent(Graphics g) {

        Tile[][] tiles = level.getTiles();
        List<Entity> entities = level.getEntities();
        Player player = (Player) entities.stream().filter((e) -> (e instanceof Player)).findFirst().get();

        Vector camera = player.getPosition();

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                draw(tiles[x][y].getSprite(), g, camera, (double) x, (double) y);
            }
        }

        entities.forEach((e) -> draw(e.getSprite(), g, camera, e.getPosition().x(), e.getPosition().y()));

    }

    private void draw(Sprite image, Graphics g, Vector c, double x, double y) {

        double screenX = (x - c.x()) * imgSize;
        double screenY = (y - c.y()) * imgSize;

        g.drawImage(image.sprite, (int) screenX, (int) screenY, null);
    }
}
