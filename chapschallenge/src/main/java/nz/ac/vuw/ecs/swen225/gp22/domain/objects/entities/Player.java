package nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities;

import java.util.HashMap;
import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.util.Direction;
import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Item;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;

public class Player extends Entity {
    private Direction facing;
    private HashMap<Item, Integer> inventory = new HashMap<>();

    public Player(Vector pos) {
        super(pos);
        this.facing = Direction.Down;
    }

    /**
     * This method should be called every frame, taking inputs and updating the
     * player accordingly.
     */
    public void update() {

    }

    public HashMap<Item, Integer> inventory() {
        return inventory;
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

        Vector newPos = getPosition().add(dir.vector());

        // TODO: Finish this.
        if (Game.getInstance().getInteractablesAt(newPos).size() == 0) {
            setPosition(newPos);
            facing = Direction.fromVector(newPos);
            return true;
        }
        return false;
    }
}