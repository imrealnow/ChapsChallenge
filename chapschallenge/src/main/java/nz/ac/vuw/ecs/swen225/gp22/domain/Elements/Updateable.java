package nz.ac.vuw.ecs.swen225.gp22.domain.elements;

/**
 * The base interface for all game elements that need behaviour updated every
 * frame.
 * Examples of such things include the player, monsters, and/or obstacles.
 */
public interface Updateable {
    public void update();
}