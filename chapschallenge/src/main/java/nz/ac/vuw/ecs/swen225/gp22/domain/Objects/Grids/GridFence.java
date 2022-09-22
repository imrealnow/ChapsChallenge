package main.java.nz.ac.vuw.ecs.swen225.gp22.domain.Objects.Grids;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Grid;

public class GridFence extends Grid{
    @Override
    public Sprite getSprite(){
        return Sprite.GridFence;
    }
}