package nz.ac.vuw.ecs.swen225.gp22.domain.Elements;

import nz.ac.vuw.ecs.swen225.gp22.domain.Objects.Entities.Player;
import nz.ac.vuw.ecs.swen225.gp22.util.Direction;
import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

/**
 * Represents an instance that can be picked up.
 */
public class Pickup implements Entity, Interactable {
    private Vector position;
    private Item item;

    @Override
    public void update() {}

    @Override
    /*
     * Whether or not a given entity should be able to step on top of this Pickup.
     * @returns Always returns true, as Pickups are not solid objects.
     */
    public boolean entityCanStep(Entity e) {
        return true;
    }

    @Override
    public void onInteract(Entity e) {
        if (e instanceof Player p){
            p.inventory().put(item,p.inventory().getOrDefault(item,0)+1);
        }
    }

    @Override
    public boolean tryMove(Direction dir) {
        //Return false, pickups cannot move
        return false;
    }

    @Override
    public Sprite getSprite() {
        return item.sprite();
    }

    @Override
    public Vector getPosition() {
        return position;
    }

    public Item getItemType(){
        return item;
    }


}