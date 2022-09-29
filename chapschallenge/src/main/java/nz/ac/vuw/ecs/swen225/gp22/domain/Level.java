package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Grid;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Item;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Pickup;

public class Level {
    private List<Entity> entities = new ArrayList<Entity>();
    private Tile[][] tiles;
    private String title;
    private int timelimit;
    private int totalFriendsNeeded;
    private Player player;
    private final int levelID;

    public Level(int ID, Tile[][] tiles, List<Entity> entities, String title, int time) {
        this.levelID = ID;
        timelimit = time;
        totalFriendsNeeded = 0;
        this.tiles = tiles;
        this.title = title;
        this.entities = new ArrayList<Entity>();
        entities.forEach(e -> {
            this.entities.add(e);
            if (e instanceof Pickup p && p.getItemType() == Item.ItemFriend)
                totalFriendsNeeded++;
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

    public void validateLevelState(){
        //Initialise Data
        int playerCount = 0;
        int collectedFriends = 0;
        int remainingFriends = 0;
        List<Entity> entitiesOnGrids = new ArrayList<Entity>();
        List<Entity> entitiesOutOfBounds = new ArrayList<Entity>(); 
        
        //Collect Data
        for(Entity e: entities){
            //Count players
            if (e instanceof Player p) {
                playerCount++;
                collectedFriends = p.inventory().getOrDefault(Item.ItemFriend, 0);
            }
            //Count friends remaining
            if (e instanceof Pickup p){
                if (p.getItem() == Item.ItemFriend) remainingFriends++;
            }

            //If entity is out of bounds
            if (e.getPosition().x() > tiles.length
                || e.getPosition().y() > tiles[0].length
                || e.getPosition().x() < 0
                || e.getPosition().y() < 0) 
                entitiesOutOfBounds.add(e);

            //If entity is on a grid (invalid position)
            if (entitiesOutOfBounds.contains(e)) continue;
            if (tiles[(int)e.getPosition().y()][(int)e.getPosition().x()] instanceof Grid) 
                entitiesOnGrids.add(e);
        }

        //Verify Data
        String output = "VALIDATION LOG:\n";
        if (playerCount != 1) 
            output += "Error: Invalid number of players! "
            +"(Expected 1, found " + playerCount +".)\n";
        if (collectedFriends + remainingFriends != totalFriendsNeeded) 
            output += "Error: Invalid sum of friends on board and friends collected! "
            +"( Expected: " + totalFriendsNeeded + ", found " + (collectedFriends + remainingFriends)
            +". C: "+ collectedFriends + " || R: " + remainingFriends + ".)\n";
        if (entitiesOnGrids.size() != 0) 
            output += "Error: Found entities on grid! "
            + "(Found: " + entitiesOnGrids.toString() + ", Expected none.)\n";
        if (entitiesOutOfBounds.size() != 0) 
            output += "Error: Found entities out of bounds! "
            + "(Found: " + entitiesOutOfBounds.toString() + ", Expected none.)\n";

        if (!output.equals("VALIDATION LOG:\n")) throw new IllegalStateException(output);
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
        return totalFriendsNeeded;
    }

    public int getLevelID(){
        return levelID;
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