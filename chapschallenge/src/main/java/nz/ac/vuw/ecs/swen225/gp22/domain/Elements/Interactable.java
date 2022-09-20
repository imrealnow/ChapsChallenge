package nz.ac.vuw.ecs.swen225.gp22.domain.Elements;

/**
 * The base interface for all game elements that need behaviour updated every
 * frame.
 * Examples of such things include the player, monsters, and/or obstacles.
 */
public interface Interactable {
    /**
     * Determines whether the specified entity will be able to step on top of this.
     * @param e The entity to check collisions with
     * @return True if the entity is allowed to move onto it, false if not.
     */
    public boolean entityCanStep(Entity e);

    /**
     * The action to be called when the specified entity steps on top of this.
     * @param e The entity to check with. This will almost always be the player.
     */
    public void onInteract(Entity e);
}