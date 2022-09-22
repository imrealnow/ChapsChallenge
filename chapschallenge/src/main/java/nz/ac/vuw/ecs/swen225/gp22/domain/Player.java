package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.util.Direction;
import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

public class Player implements Entity {
    private Vector position;
    private Direction facing;

    public Player(Vector pos){
        this.position = pos;
        this.facing = Direction.Down;
    }

    /**
     * This method should be called every frame, taking inputs and updating the
     * player accordingly.
     */
    public void update() {

    }

    public Vector getPosition() {
        return position;
    }

    public Direction getDirection() {
        return facing;
    }

    public Sprite getSprite() {
        switch (facing) {
            case Up:
                return Sprite.LegendUp;
            case Down:
                return Sprite.LegendDown;
            case Left:
                return Sprite.LegendLeft;
            case Right:
                return Sprite.LegendRight;
        }
        return Sprite.LegendDown;
    }

    public boolean tryMove(Vector newPos) {

        //Force the player to move one tile at a time
        if (position.distance(newPos) > 1){
            return false;
        }

        // TODO: Finish this.
        if (Game.getInteractablesAt(newPos).size() == 0) {
            position = newPos;
            facing = Direction.fromVector(newPos);
            return true;
        }
        return false;
    }
}