package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

import java.util.List;

import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.domain.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;


public class GameView extends JPanel {

    int imgSize = 32;

    Level level;

    GameView(Level level) {
        this.level = level;
    }
    

    @Override
    public void paintComponent(Graphics g) {

        Tile[][] tiles = level.getTiles();
        List<Entity> entities = level.getEntities();
        Player player = (Player)entities.stream().filter((e) -> (e instanceof Player)).findFirst().get();

        Vector camera = player.getVector();

        for (Tile[] tileList : tiles) {
            for (Tile tile : tileList) {
                drawTile(tile, g, camera);
            }
        }

        entities.forEach((e) -> drawEntity(e, g, camera));

    }

    public void drawTile(Tile t, Graphics g, Vector c) {
        Vector tileVector = t.getVector();
        BufferedImage image = t.getImage();

        double x = (tileVector.x()-c.x())*imgSize;
        double y = (tileVector.y()-c.y())*imgSize;

        g.drawImage(image, (int)x, (int)y, null);
    }

    public void drawEntity(Entity e, Graphics g, Vector c) {
        Vector entityVector = e.getVector();
        BufferedImage image = e.getImage();

        double x = (entityVector.x()-c.x())*imgSize;
        double y = (entityVector.y()-c.y())*imgSize;

        g.drawImage(image, (int)x, (int)y, null);
    }


}
