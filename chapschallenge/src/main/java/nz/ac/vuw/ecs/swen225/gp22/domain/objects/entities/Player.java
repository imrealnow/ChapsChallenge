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

    // runs queued command
    public void update() {
        super.update();
        // do anything else you need, but always call super.update() first
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

    @Override
    protected void applyMove(Direction dir, List<Interactable> interactables) {
        super.applyMove(dir, interactables);
        facing = dir;
    }
}