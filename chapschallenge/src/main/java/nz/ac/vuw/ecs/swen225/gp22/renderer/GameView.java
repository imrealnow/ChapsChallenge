package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids.TileInfo;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.util.TickedTask;
import nz.ac.vuw.ecs.swen225.gp22.util.Time;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

public class GameView extends JPanel {
    private static final int imgSize = 64;
    private final TickedTask mainUpdateLoop;

    private Level level;
    private List<Entity> entities = new ArrayList<>();
    private Tile[][] tiles;
    private Player player;
    private Vector camera;

    public GameView(Level level) {
        this.level = level;
        updateInfo();
        player = level.getPlayer();
        camera = player.getPosition();
        GameView self = this;
        mainUpdateLoop = Time.INSTANCE.getLoopTask(Game.UPDATE_KEY).get();
        mainUpdateLoop.setPostCallback(() -> {
            self.updateInfo();
            self.repaint();
        });
    }

    public void updateInfo() {
        this.tiles = level.getTiles();
        this.entities = level.getEntities();
        this.player = level.getPlayer();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        camera = camera.smoothInterpolate(player.getPosition(), 0.1);

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                draw(tiles[y][x].getSprite(), g, camera, (double) x, (double) y, imgSize, imgSize);
            }
        }

        entities.forEach((e) -> draw(e.getSprite(), g, camera, e.getPosition().x(), e.getPosition().y(), imgSize, imgSize));
        
        Tile playerTile = tiles[(int)player.getPosition().y()][(int)player.getPosition().x()];
        if (playerTile instanceof TileInfo infoTile) {
            draw(infoTile.getInfoGraphic(), g, camera, camera.x(), camera.y()-1, imgSize*6, imgSize*2);
        }
    }

    private void draw(Sprite image, Graphics g, Vector c, double x, double y, int imgX, int imgY) {
        Dimension s = getSize();
        double screenX = (x - c.x()) * imgX + s.getWidth() / 2 - imgX / 2;
        double screenY = (y - c.y()) * imgY + s.getHeight() / 2 - imgY / 2;

        g.drawImage(image.sprite, (int) screenX, (int) screenY, imgX, imgY, null);
    }
}
