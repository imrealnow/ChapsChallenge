package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids.*;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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

    private final JLabel label;
    private Tile selectedTile;
    private final TileSelection[] tiles;
    private TileSelection currentSelection;

    TilePalette() {
        super(new BorderLayout());
        label = new JLabel("Tile Palette");
        add(label, BorderLayout.NORTH);
        Tile[] tileTypes = TILE_TYPES;
        this.tiles = new TileSelection[tileTypes.length];
        JPanel tilePanel = new JPanel(new GridLayout(tiles.length, 1));
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new TileSelection(tileTypes[i], i, this);
            tilePanel.add(tiles[i]);
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
        currentSelection = tiles[selectedTile.index()];
        currentSelection.highlight();
        this.selectedTile = selectedTile.getTile();
    }
}
