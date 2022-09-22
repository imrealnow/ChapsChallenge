package nz.ac.vuw.ecs.swen225.gp22.domain.Elements;

import java.awt.image.BufferedImage;

/**
 * A Tile represents a non-moving element on the board.
 * These are any tiles, whether or not the player has any collisions with them.
 */
public abstract class Tile {
    private final int x;
    private final int y;
    private final Sprite sprite;

    /**
     * Constructs a tile at the specified position.
     * 
     * @param x
     * @param y
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract Sprite getSprite();
}