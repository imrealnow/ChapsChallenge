package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

public class Player implements Entity {
    private Vector position;

    /**
     * This method should be called every frame, taking inputs and updating the
     * player accordingly.
     */
    public void update() {

    }
    
    public boolean tryMove(Vector newPos, Game game){

        //TODO: Finish this.
        if (game.getInteractablesAt(newPos).length() == 0) { return true;}
        return false;
    }
}