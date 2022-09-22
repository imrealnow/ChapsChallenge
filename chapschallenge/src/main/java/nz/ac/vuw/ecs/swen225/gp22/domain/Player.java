package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

public class Player implements Entity {
    private Vector position;

    /**
     * This method should be called every frame, taking inputs and updating the
     * player accordingly.
     */
    public void update() {

    }

    public Vector getPosition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Sprite getSprite() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean tryMove(Vector newPos) {

        // TODO: Finish this.
        if (Game.getInteractablesAt(newPos).size() == 0) {
            return true;
        }
        return false;
    }
}