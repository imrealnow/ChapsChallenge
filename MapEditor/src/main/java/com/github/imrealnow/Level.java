package com.github.imrealnow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Level {
    private List<Entity> entities = new ArrayList<Entity>();
    private Tile[][] tiles;
    String title;
    int timelimit;

    public Level(Tile[][] tiles, List<Entity> entities, String title, int timeLimit) {
        this.timelimit = timeLimit;
        this.tiles = tiles;
        this.title = title;
        this.entities = new ArrayList<Entity>();
    }

    /**
     * Returns an arraylist of all entities in the current level.
     */
    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }

    /**
     * Returns an array of all tiles in the current level.
     * 
     * @return The 2d array of tiles of the level.
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Returns the title of the current level.
     * 
     * @param tiles The tiles to set the level to.
     */
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
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