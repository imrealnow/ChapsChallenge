package nz.ac.vuw.ecs.swen225.gp22.util;

import java.awt.image.BufferedImage;

/**
 * A Sprite is a Buffered Image but with added capabilities.
 * Right now, the class is just a wrapper for BufferedImages,
 * but added expansion could occur in the future.
 * 
 * @author Bradley Cave
 */
public class Sprite {

    BufferedImage sprite;

    /*
     * Constructs a sprite object.
     * 
     */
    public Sprite(BufferedImage s) {
        this.sprite = s;
    }

    public BufferedImage getBufferedImage() {
        return sprite;
    }
}