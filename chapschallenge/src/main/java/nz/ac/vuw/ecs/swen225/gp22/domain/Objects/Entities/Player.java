package nz.ac.vuw.ecs.swen225.gp22.domain.Objects.Entities;

import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Entity;

import java.util.HashMap;

import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.util.Direction;
import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Item;

public class Player implements Entity {
    private Vector position;
    private Direction facing;
    private HashMap<Item,Integer> inventory = new HashMap<>();

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

    public HashMap<Item,Integer> inventory() {
        return inventory;
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

    public boolean tryMove(Direction dir) {

        Vector newPos = position.add(dir.vector());

        // TODO: Finish this.
        if (Game.getInteractablesAt(newPos).size() == 0) {
            position = newPos;
            facing = Direction.fromVector(newPos);
            return true;
        }
        return false;
    }
}