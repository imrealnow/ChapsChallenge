package nz.ac.vuw.ecs.swen225.gp22.domain.Objects.Grids;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Grid;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Entity;

public class GridLockBlue extends Grid {
    @Override
    public Sprite getSprite() {
        return Sprite.GridLockBlue;
    }

    @Override
    public boolean entityCanStep(Entity e) {
        // TODO: Implement Player interaction Behaviour
        return false;
    }

    @Override
    public void onInteract(Entity e) {
        // TODO: Implement Player interaction Behaviour
    }
}