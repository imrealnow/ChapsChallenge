package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;

/**
 * Tile Selection component that allows the user to select a tile from the
 * palette
 */
public class TileSelection extends JButton {
    private int index;
    private Tile tile;
    private final TilePalette palette;

    TileSelection(Tile tile, int index, TilePalette palette) {
        super(new ImageIcon(tile.getSprite().sprite));
        setBorder(BorderFactory.createEmptyBorder());
        setToolTipText(tile.getClass().getSimpleName());
        setContentAreaFilled(false);
        this.index = index;
        this.tile = tile;
        this.palette = palette;
        setSize(32, 32);
        addActionListener(e -> {
            palette.setSelectedTile(this);
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(32, 32);
    }

    public int index() {
        return index;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void highlight() {
        setBorder(BorderFactory.createLineBorder(new Color(77, 166, 255), 3));
    }

    public void unhighlight() {
        setBorder(BorderFactory.createEmptyBorder());
    }
}
