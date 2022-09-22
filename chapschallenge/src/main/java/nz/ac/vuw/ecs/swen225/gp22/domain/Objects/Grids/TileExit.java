package nz.ac.vuw.ecs.swen225.gp22.domain.Objects.Grids;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Interactable;

public class TileExit extends Tile implements Interactable {
    @Override
    public Sprite getSprite() {
        return Sprite.TileExit;
    }

    @Override
    public boolean entityCanStep(Entity e) {
        // A player should always be able to walk onto this tile
        return true;
    }

    @Override
    public void onInteract(Entity e) {
        // TODO Auto-generated method stub

    }
}