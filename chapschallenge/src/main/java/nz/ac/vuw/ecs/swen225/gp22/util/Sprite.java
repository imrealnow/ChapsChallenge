package nz.ac.vuw.ecs.swen225.gp22.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * All sprites are defined as Enumerators.
 * Adapted from
 * http://www.java2s.com/example/java-utility-method/bufferedimage-load/loadimage-file-file-b9b68.html
 * 
 * @author Bradley Cave
 */
public enum Sprite {
    // Tiles
    TileGrass,
    TilePath,
    TileInfo,
    TileExit,

    // Grids
    GridTree,
    GridFence,

    // Locks
    GridLockBlue,
    GridLockRed,
    GridLockYellow,

    // Keys
    KeyBlue,
    KeyRed,
    KeyYellow,

    // Player
    LegendDown,
    LegendRight,
    LegendUp,
    LegendLeft,

    // Special
    Friend,
    George;

    public final BufferedImage sprite;

    Sprite() {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO
                    .read(new File("..\\..\\..\\..\\..\\..\\..\\..\\..\\resources\\images\\" + this.name() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.sprite = sprite;
        }
    }

}