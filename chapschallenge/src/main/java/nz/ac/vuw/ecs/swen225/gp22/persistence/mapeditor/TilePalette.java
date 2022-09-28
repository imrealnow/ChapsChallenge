package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * Tile palette that holds all the tiles and allows the user to select one
 * to be painted on the grid.
 */
public class TilePalette extends JPanel {
    private static Tile[] TILE_TYPES = {
            new TileGrass(),
            new TileInfo(),
            new TilePath(),
            new TileExit(),
            new GridFence(),
            new GridLockBlue(),
            new GridLockRed(),
            new GridLockSilver(),
            new GridLockYellow(),
            new GridTree()
    };
    private Tile selectedTile;
    private final TileSelection[] tiles;
    private TileSelection currentSelection;

    TilePalette() {
        super();
        this.tiles = new TileSelection[TILE_TYPES.length];
        add(createTilePanel());
    }

    private JPanel createTilePanel() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.ipadx = 5;
        constraints.ipady = 5;
        JPanel tilePanel = new JPanel(layout);
        tilePanel.setBackground(Color.lightGray);
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new TileSelection(TILE_TYPES[i], i, this);
            constraints.weightx = 0.5;
            constraints.weighty = 0;
            constraints.gridx = i % 5;
            constraints.gridy = i / 5;
            tilePanel.add(tiles[i], constraints);
        }
        return tilePanel;
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedTile(TileSelection selectedTile) {
        if (currentSelection != null) {
            currentSelection.unhighlight();
        }
        currentSelection = tiles[selectedTile.index()];
        currentSelection.highlight();
        this.selectedTile = selectedTile.getTile();
    }
}
