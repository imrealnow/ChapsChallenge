package com.github.imrealnow;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * JComponent that represents a tile
 */
public class TileComponent extends JComponent {
    private Tile tile;
    private final TileGrid gridPanel;

    public static TileComponent empty(TileGrid gridPanel) {
        return new TileComponent(Tile.Grass, gridPanel);
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    TileComponent(Tile tile, TileGrid gridPanel) {
        this.tile = tile;
        this.gridPanel = gridPanel;
        setSize(gridPanel.getCellDimension());
    }

    public void draw(Graphics g, int x, int y) {
        Dimension d = gridPanel.getCellDimension();
        g.setColor(tile.getColor());
        g.fillRect(x, y, d.width, d.height);
        g.setColor(Color.black);
    }
}
