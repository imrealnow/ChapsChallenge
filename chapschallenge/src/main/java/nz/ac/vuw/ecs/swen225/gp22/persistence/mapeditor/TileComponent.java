package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import java.awt.Graphics;
import javax.swing.JComponent;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids.TileGrass;
import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;

/**
 * JComponent that represents a tile
 */
public class TileComponent extends JComponent {
    private Tile tile;
    private final TileGrid gridPanel;

    TileComponent(Tile tile, TileGrid gridPanel) {
        this.tile = tile;
        this.gridPanel = gridPanel;
    }

    public static TileComponent empty(TileGrid gridPanel) {
        return new TileComponent(new TileGrass(), gridPanel);
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void draw(Graphics g, int x, int y, int width, int height) {
        g.drawImage(tile.getSprite().sprite, x, y, width, height, null);
    }
}
