package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids.TileGrass;
import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

/**
 * JComponent that represents a tile
 */
public class TileComponent extends JButton {
    private Tile tile;
    private final TileGrid gridPanel;
    private final Vector position;

    TileComponent(Tile tile, TileGrid gridPanel, Vector position) {
        this.tile = tile;
        this.position = position;
        this.gridPanel = gridPanel;
        setBorder(BorderFactory.createEmptyBorder());
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        addChangeListener(l -> {
            if (getModel().isRollover()) {
                setBorder(BorderFactory.createLineBorder(new java.awt.Color(77, 166, 255), 3));
            } else {
                setBorder(BorderFactory.createEmptyBorder());
            }
        });
    }

    public static TileComponent empty(TileGrid gridPanel, Vector position) {
        return new TileComponent(new TileGrass(), gridPanel, position);
    }

    public Vector getPosition() {
        return position;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void draw(Graphics g) {
        Rectangle rect = getBounds();
        g.drawImage(tile.getSprite().sprite, rect.x, rect.y, rect.width, rect.height, null);
    }

    @Override
    public Dimension getPreferredSize() {
        Rectangle rect = getBounds();
        return new Dimension(rect.width, rect.height);
    }

    // @Override
    // public void paintComponent(Graphics g) {
    // super.paintComponent(g);
    // g.drawImage(tile.getSprite().sprite, 0, 0, rect.width, rect.height, null);
    // }
}
