package nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;

public class TileGrass extends Tile {
    @Override
    public Sprite getSprite() {
        return Sprite.TileGrass;
    }
}