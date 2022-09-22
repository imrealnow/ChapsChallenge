package nz.ac.vuw.ecs.swen225.gp22.domain.Elements;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;

/**
 * A Tile represents a non-moving element on the board.
 * These are any tiles, whether or not the player has any collisions with them.
 */
public abstract class Tile {
    // private final Sprite sprite;
    // tile needs a position

    // sprite doesn't change, so it can just be made in the method
    public abstract Sprite getSprite();
}