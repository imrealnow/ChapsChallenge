package nz.ac.vuw.ecs.swen225.gp22.domain.Objects.Grids;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Tile;

public class TilePath extends Tile{
    @Override
    public Sprite getSprite(){
        return Sprite.TilePath;
    }
}