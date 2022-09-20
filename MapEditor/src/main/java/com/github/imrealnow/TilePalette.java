package com.github.imrealnow;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

/**
 * Tile palette that holds all the tiles and allows the user to select one
 * to be painted on the grid.
 */
public class TilePalette extends JPanel {
    private final JLabel label;
    private Tile selectedTile;
    private final TileSelection[] tiles;
    private TileSelection currentSelection;

    TilePalette() {
        super(new BorderLayout());
        label = new JLabel("Tile Palette");
        add(label, BorderLayout.NORTH);
        Tile[] tiles = Tile.values();
        this.tiles = new TileSelection[tiles.length];
        JPanel tilePanel = new JPanel(new GridLayout(tiles.length, 1));
        for (Tile tile : tiles) {
            TileSelection tileSelection = new TileSelection(tile, this);
            this.tiles[tile.getId()] = tileSelection;
            tilePanel.add(tileSelection);
        }
        add(tilePanel);
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedTile(TileSelection selectedTile) {
        if (currentSelection != null) {
            currentSelection.unhighlight();
        }
        currentSelection = tiles[selectedTile.getTile().getId()];
        currentSelection.highlight();
        this.selectedTile = selectedTile.getTile();
    }
}
