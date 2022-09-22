package nz.ac.vuw.ecs.swen225.gp22.util;

import java.awt.image.BufferedImage;

/**
 * All sprites are defined as Enumerators.
 * Adapted from http://www.java2s.com/example/java-utility-method/bufferedimage-load/loadimage-file-file-b9b68.html
 * 
 * @author Bradley Cave
 */
public enum Sprite {
    //Tiles
        TileGrass,
        TilePath,
        TileInfo,
        TileExit,
    
    //Grids
        GridTree,
        GridFence,

    //Locks
        GridLockBlue,
        GridLockRed,
        GridLockYellow,

    //Keys
        KeyBlue,
        KeyRed,
        KeyYellow,
    
    //Player
        LegendDown,
        LegendRight,
        LegendUp,
        LegendLeft,

    //Special
        Friend,
        George;
    
    public BufferedImage sprite;

    Sprite(){
        try {
            sprite = ImageIO.read("..\\..\\..\\..\\..\\..\\..\\..\\..\\resources\\images\\"+this.name()+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}