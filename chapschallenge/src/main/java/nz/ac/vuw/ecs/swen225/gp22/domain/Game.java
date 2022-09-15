package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Represents the Game object, which contains the current level and various
 * stats related to the game.
 */
public class Game {
    private Level currentLevel;

    /**
     * Called every frame, causing all of the relevant game elements to call their
     * update methods.
     */
    private void update() {
        currentLevel.getEntities().forEach(entity -> entity.update());
    }
}