package com.github.imrealnow;

import java.awt.Color;

/**
 * Class that represents a tile in the level.
 */
public enum Tile {
    Empty(0, "Blank", ' ', Color.lightGray),
    Wall(1, "Wall", '#', Color.darkGray),
    Player(2, "Player", '@', Color.blue),
    Exit(3, "Exit", 'E', Color.green);

    Tile(int id, String name,
            char symbol, Color color) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.color = color;
    }

    private final int id;
    private final String name;
    private final char symbol;
    private final Color color;

    public static Tile getFromId(int id) {
        for (Tile tile : values()) {
            if (tile.id == id) {
                return tile;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public Color getColor() {
        return color;
    }
}
