package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Item;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Pickup;

public class Level {
    private List<Entity> entities = new ArrayList<Entity>();
    private Tile[][] tiles;
    String title;
    int timelimit;
    int friendsNeeded;
    Player player;

    public Level(Tile[][] tiles, List<Entity> entities, String title, int time) {
        timelimit = time;
        friendsNeeded = 0;
        this.tiles = tiles;
        this.title = title;
        this.entities = new ArrayList<Entity>();
        entities.forEach(e -> {
            this.entities.add(e);
            if (e instanceof Pickup p && p.getItemType() == Item.ItemFriend)
                friendsNeeded++;
            if (e instanceof Player p) {
                if (this.player != null) throw new IllegalArgumentException(
                    "Cannot have more than one instance of player in Level");
                this.player = p;
            }
        });
        if (player == null) throw new IllegalArgumentException("No player found in Level");
    }

    public void morphTile(Tile oldTile, Tile newTile){
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                if (tiles[x][y] == oldTile) {
                    tiles[x][y] = newTile;
                    return;
                }
            }
        }    
    }

    public void removeEntity(Entity e){
        entities.remove(e);
    }

    /**
     * Returns the player object
     */
    public Player getPlayer() {
        return player;
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

    /**
     * Returns the title of the current level.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the time limit of the current level.
     */
    public int getTimeLimit() {
        return timelimit;
    }

    /**
     * Returns the number of friends needed to complete the level
     */
    public int getFriendsNeeded() {
        return friendsNeeded;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Level))
            return false;

        Level level = (Level) o;
        for (int i = 0; i < entities.size(); i++) {
            if (!entities.get(i).equals(level.entities.get(i))) {
                return false;
            }
        }
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                // compare dynamic class of each tile
                if (!tiles[x][y].getClass().getSimpleName().equals(level.getTiles()[x][y].getClass().getSimpleName())) {
                    return false;
                }
            }
        }
        return level.getTitle().equals(title) && level.getTimeLimit() == timelimit;
    }
}