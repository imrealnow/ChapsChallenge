package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import java.awt.Color;
import java.awt.Dimension;

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
        super(tile.getClass().getSimpleName());
        this.index = index;
        this.tile = tile;
        this.palette = palette;
        setPreferredSize(new Dimension(getWidth(), 60));
        addActionListener(e -> {
            palette.setSelectedTile(this);
        });
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
        setBackground(new Color(83, 158, 219));
    }

    public void unhighlight() {
        setBackground(null);
    }
}
