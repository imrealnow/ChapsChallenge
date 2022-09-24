package com.github.imrealnow;

import java.awt.Color;

/**
 * Class that represents a tile in the level.
 */
public enum Tile {
    Grass("TileGrass", new Color(204, 255, 153)),
    Exit("TileExit", new Color(102, 255, 255)),
    Info("TileInfo", new Color(255, 153, 102)),
    Path("TilePath", new Color(255, 238, 204)),
    Fence("GridFence", new Color(179, 89, 0)),
    LockBlue("GridLockBlue", new Color(51, 102, 255)),
    LockRed("GridLockRed", new Color(204, 0, 0)),
    LockYellow("GridLockYellow", new Color(204, 153, 0)),
    Tree("GridTree", new Color(0, 153, 0));

    Tile(String tileSimpleName, Color color) {
        this.tileSimpleName = tileSimpleName;
        this.color = color;
    }

    private final String tileSimpleName;
    private final Color color;

    public static Tile[][] blankGrid(int width, int height) {
        Tile[][] grid = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = Tile.Grass;
            }
        }
        return grid;
    }

    public static Tile getFromName(String simpleName) {
        for (Tile tile : values()) {
            if (tile.tileSimpleName.equals(simpleName)) {
                return tile;
            }
        }
        return null;
    }

    public String getName() {
        return tileSimpleName;
    }

    public Color getColor() {
        return color;
    }
}
