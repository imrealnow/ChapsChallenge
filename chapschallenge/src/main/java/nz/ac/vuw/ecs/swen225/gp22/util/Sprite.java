package nz.ac.vuw.ecs.swen225.gp22.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
    TileGrass("TileGrass.png"),
    TilePath("TilePath.png"),
    TileInfo("TileInfo.png"),
    TileExit("TileExit.png"),

    // Grids
    GridTree("GridTree.png"),
    GridFence("GridFence.png"),

    // Locks
    GridLockBlue("GridLockBlue.png"),
    GridLockRed("GridLockRed.png"),
    GridLockYellow("GridLockYellow.png"),
    GridLockSilver("GridLockSilver.png"),

    // Keys
    KeyBlue("KeyBlue.png"),
    KeyRed("KeyRed.png"),
    KeyYellow("KeyYellow.png"),
    KeySilver("KeySilver.png"),

    // Player
    LegendDown("LegendDown.png"),
    LegendRight("LegendRight.png"),
    LegendUp("LegendUp.png"),
    LegendLeft("LegendLeft.png"),

    // Special
    Friend("Friend.png"),
    GridGeorge("George.png"),

    //Info Tile Hints
    Info1("info/Level1.png"),
    Info2("info/Level2.png");

    public BufferedImage sprite;

    Sprite(String fileName) {
        try {
            sprite = ImageIO.read(getClass().getResource("/images/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}