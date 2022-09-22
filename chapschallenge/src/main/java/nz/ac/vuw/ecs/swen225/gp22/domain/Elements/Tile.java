package nz.ac.vuw.ecs.swen225.gp22.domain.Elements;

import java.awt.image.BufferedImage;

/**
 * A Tile represents a non-moving element on the board.
 * These are any tiles, whether or not the player has any collisions with them.
 */
public abstract class Tile {
    private final Sprite sprite;
    
    public abstract Sprite getSprite();
}