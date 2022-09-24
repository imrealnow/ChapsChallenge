package com.github.imrealnow;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

/**
 * Tile Selection component that allows the user to select a tile from the
 * palette
 */
public class TileSelection extends JButton {
    private Tile tile;
    private final TilePalette palette;

    TileSelection(Tile tile, TilePalette palette) {
        super(tile.getName());
        this.tile = tile;
        this.palette = palette;
        setPreferredSize(new Dimension(getWidth(), 60));

        addActionListener(e -> {
            palette.setSelectedTile(this);
        });
    }

    public Tile getTile() {
        return tile;
    }

    public void highlight() {
        setBackground(new Color(83, 158, 219));
    }

    public void unhighlight() {
        setBackground(null);
    }
}
