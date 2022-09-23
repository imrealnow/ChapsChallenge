package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Item;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Pickup;

public class Level {
    private List<Entity> entities = new ArrayList<Entity>();
    private Tile[][] tiles;
    String title;
    int timelimit;
    int friendsNeeded;

    public Level(Tile[][] tiles, List<Entity> entities, String title, int time) {
        timelimit = time;
        friendsNeeded = 0;
        this.tiles = tiles;
        this.entities = new ArrayList<Entity>();
        entities.forEach(e -> {
            this.entities.add(e);
            if (e instanceof Pickup p && p.getItemType() == Item.ItemFriend)
                friendsNeeded++;
        });
    }

    /**
     * Returns an arraylist of all entities in the current level.
     */
    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }

    /**
     * Returns an array of all tiles in the current level.
     */
    public Tile[][] getTiles() {
        return tiles;
    }
}