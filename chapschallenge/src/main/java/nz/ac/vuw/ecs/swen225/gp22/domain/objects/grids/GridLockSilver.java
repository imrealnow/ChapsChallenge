package nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Grid;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;

public class GridLockSilver extends Grid {
    @Override
    public Sprite getSprite() {
        return Sprite.GridLockSilver;
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