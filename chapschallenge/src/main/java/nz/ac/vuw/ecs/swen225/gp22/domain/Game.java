package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Interactable;
import nz.ac.vuw.ecs.swen225.gp22.util.Time;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

/**
 * Represents the Game object, which contains the current level and various
 * stats related to the game.
 */
public class Game {
    private Level currentLevel;
    public static final String UPDATE_KEY = "updateLoop";
    public static final int UPDATE_PER_SECOND = 3;

    Game(Level level) {
        this.currentLevel = level;
        Time.INSTANCE.loop(UPDATE_KEY, UPDATE_PER_SECOND, () -> update());
    }

    /**
     * Called every frame, causing all of the relevant game elements to call their
     * update methods.
     */
    public void update() {
        currentLevel.getEntities().forEach(entity -> entity.update());
    }

    /**
     * Returns the current level.
     * 
     * @return The current game level.
     */
    public Level getLevel() {
        return currentLevel;
    }

    /**
     * Set the new level.
     * 
     */
    public void setLevel(Level newLevel) {
        this.currentLevel = newLevel;
    }

    public List<Interactable> getInteractablesAt(Vector pos) {
        List<Interactable> is = new ArrayList<Interactable>();

        // TODO: Finish this.
        // I will implement this later.
        // Recuse through level entities and grids, returnin all interactables at
        // (or around????) the specified position.
        return is;
    }
}