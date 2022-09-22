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
    private static Level currentLevel;
    public static final String UPDATE_KEY = "updateLoop";
    public static final int UPDATE_PER_SECOND = 3;

    Game(Level level) {
        currentLevel = level;
        Time.INSTANCE.loop(UPDATE_KEY, UPDATE_PER_SECOND, () -> update());
    }

    /**
     * Called every frame, causing all of the relevant game elements to call their
     * update methods.
     */
    public static void update() {
        currentLevel.getEntities().forEach(entity -> entity.update());
    }

    /**
     * Returns the current level.
     * 
     * @return The current game level.
     */
    public static Level getLevel() {
        return currentLevel;
    }

    /**
     * Set the new level.
     * 
     */
    public static void setLevel(Level newLevel) {
        currentLevel = newLevel;
    }

    /**
     * Gets all the interactable elements at the given position.
     * Typically used in tandem with onInteract(e).
     * 
     * @param pos
     * @return A list of all interactable tiles at this position.
     */
    public static List<Interactable> getInteractablesAt(Vector pos) {
        List<Interactable> is = new ArrayList<Interactable>();

        currentLevel.getEntities().forEach(i -> {
            if ( i.getPosition().x() == pos.x() 
                && i.getPosition().y()  == pos.y()
                && i instanceof Interactable intEnt) {
                    is.add(intEnt);
                }
            }); 

        if (currentLevel.getTiles()[(int)pos.x()][(int)pos.y()] instanceof Interactable intTile) is.add(intTile);

        return is;
    }
}