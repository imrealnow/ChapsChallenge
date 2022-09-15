package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Tile;

public class Level {
    private List<Entity> entities = new ArrayList<Entity>();
    private List<Tile> tiles = new ArrayList<Tile>();

    /**
     * Returns an arraylist of all entities in the current level.
     */
    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }

    /**
     * Returns an arraylist of all tiles in the current level.
     * This function may not be necessary.
     */
    public List<Tile> getTiles() {
        return Collections.unmodifiableList(tiles);
    }
}