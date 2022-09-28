package nz.ac.vuw.ecs.swen225.gp22.domain.elements;

import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities.Player;
import nz.ac.vuw.ecs.swen225.gp22.util.Direction;
import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

/**
 * Represents an instance that can be picked up.
 */
public class Pickup extends Entity implements Interactable {
    private Item item;

    public Pickup(Vector position, Item item) {
        super(position);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void update() {
    }

    @Override
    /*
     * Whether or not a given entity should be able to step on top of this Pickup.
     * 
     * @returns Always returns true, as Pickups are not solid objects.
     */
    public boolean entityCanStep(Entity e) {
        return true;
    }

    @Override
    public void onInteract(Entity e) {
        if (e instanceof Player p) {
            p.inventory().put(item, p.inventory().getOrDefault(item, 0) + 1);
            Game.getInstance().getLevel().removeEntity(this);
        }
    }

    @Override
    public boolean tryMove(Direction dir) {
        // Return false, pickups cannot move
        return false;
    }

    @Override
    public Sprite getSprite() {
        return item.sprite();
    }

    public Item getItemType() {
        return item;
    }

}