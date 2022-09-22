package nz.ac.vuw.ecs.swen225.gp22.domain.Elements;

import java.awt.image.BufferedImage;

/**
 * A Tile represents a non-moving element on the board.
 * These are any tiles, whether or not the player has any collisions with them.
 */
public abstract class Tile {
    private final int x;
    private final int y;

    /**
     * Constructs a tile at the specified position.
     * This class is unfinished as Tiles should not be contructed without a sprite
     * to represent them.
     * 
     * @param x
     * @param y
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract BufferedImage getSprite();
}