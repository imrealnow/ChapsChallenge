package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Interactable;
import nz.ac.vuw.ecs.swen225.gp22.persistence.LevelLoader;
import nz.ac.vuw.ecs.swen225.gp22.util.Time;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

/**
 * Represents the Game object, which contains the current level and various
 * stats related to the game.
 */
public class Game {
    public static final String UPDATE_KEY = "updateLoop";
    public static final int UPDATE_PER_SECOND = 3;

    private static Game INSTANCE;

    private Level currentLevel;

    public static Game getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("Game has not been initialized");
        }
        return INSTANCE;
    }

    public Game() {
        if (INSTANCE != null)
            throw new IllegalStateException("Game already instantiated");
        INSTANCE = this;
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
        if (Time.INSTANCE.isLoopRunning(UPDATE_KEY)) {
            Time.INSTANCE.stop(UPDATE_KEY);
        }
        currentLevel = newLevel;
        Time.INSTANCE.loop(UPDATE_KEY, UPDATE_PER_SECOND, () -> update());
    }

    /**
     * Gets all the interactable elements at the given position.
     * Typically used in tandem with onInteract(e).
     * 
     * @param pos
     * @return A list of all interactable tiles at this position.
     */
    public List<Interactable> getInteractablesAt(Vector pos) {
        List<Interactable> is = new ArrayList<Interactable>();

        currentLevel.getEntities().forEach(i -> {
            if (i.getPosition().x() == pos.x()
                    && i.getPosition().y() == pos.y()
                    && i instanceof Interactable intEnt) {
                is.add(intEnt);
            }
        });

        if (currentLevel.getTiles()[(int) pos.x()][(int) pos.y()] instanceof Interactable intTile)
            is.add(intTile);

        return is;
    }
}