package main.java.nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.Collections;

public class Level{
    private List<Entity> entities = new ArrayList<Entity>();  
    private List<Tile> tiles = new ArrayList<Tile>();   

    /**
     * Returns an arraylist of all entities in the current level.
     */
    public ArrayList<Entity> getEntities(){
        return Collections.unmodifiableList(entities);
    }

    /**
     * Returns an arraylist of all tiles in the current level.
     * This function may not be necessary.
     */
    public ArrayList<Tile> getTiles(){
        return Collections.unmodifiableList(tiles);
    }
}