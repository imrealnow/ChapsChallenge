package nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities;

import java.util.HashMap;
import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.util.Direction;
import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Item;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Interactable;

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

        //Case 0: The player is moving to a valid positon
        if (Game.getInstance().getLevel().getTiles().length < newPos.x() 
            || newPos.x() < 0) 
            throw new IndexOutOfBoundsException(
                "Attempted to move player out of bounds: X=" + newPos.x());
        if (Game.getInstance().getLevel().getTiles()[0].length < newPos.y() 
            || newPos.y() < 0) 
            throw new IndexOutOfBoundsException(
                "Attempted to move player out of bounds: Y=" + newPos.y());

        //Case 1: There are no grids or entities at the desired position.
        if (Game.getInstance().getInteractablesAt(newPos).size() == 0) {
            setPosition(newPos);
            facing = Direction.fromVector(newPos);
            return true;
        }

        //Case 2: There are grids/entities at the desired position
        //Therefore, we need to check if we can step on all of them
        for (Interactable i: Game.getInstance().getInteractablesAt(newPos)){
            if (i.entityCanStep(this) == false) return false;
        }

        //Case 3: There are grids/entities at the desired position
        //But we can step on them
        for (Interactable i: Game.getInstance().getInteractablesAt(newPos)){
            i.onInteract(this);
        }
        Game.getInstance().getLevel().flushEntityCache();
        return true;
    }
}