package main.java.nz.ac.vuw.ecs.swen225.gp22.domain.Objects.Grids;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.domain.*;

public class TileGrass implements Tile{
    public Sprite getSprite(){
        return Sprite.TileGrass;
    }
}