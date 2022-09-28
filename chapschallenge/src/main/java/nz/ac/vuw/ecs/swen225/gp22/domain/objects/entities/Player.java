package nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities;

import java.util.HashMap;
import java.util.List;

import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.util.Direction;
import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Item;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
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

        Tile[][] tiles = Game.getInstance().getLevel().getTiles();
        int levelHeight = tiles.length;
        int levelWidth = tiles[0].length;

        // Case 0: The player is moving to a invalid positon
        if (newPos.x() < 0 || newPos.x() >= levelWidth || newPos.y() < 0 || newPos.y() >= levelHeight) {
            throw new IndexOutOfBoundsException("Player tried to move out of bounds");
        }

        List<Interactable> interactables = Game.getInstance().getInteractablesAt(newPos);

        // if any interactables block movement, return false
        if (interactables.stream().anyMatch(i -> !i.entityCanStep(this))) {
            applyMove(dir, interactables);
            return false;
        }

        // otherwise, move the player
        applyMove(dir, interactables);
        return true;
    }

    private void applyMove(Direction dir, List<Interactable> interactables) {
        interactables.stream().forEach(i -> i.onInteract(this));
        setPosition(getPosition().add(dir.vector()));
        facing = dir;
    }
}