package nz.ac.vuw.ecs.swen225.gp22.domain.Elements;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;

/**
 * A Grid represents a 'solid' object that should stop the player from moving on
 * top of it if they try to.
 */
public abstract class Grid extends Tile implements Interactable {
  /**
   * Refer to Interactable for description.
   * 
   * A normal grid always returns false, as there is no way to step on a normal
   * Grid (such as a wall)
   * This method should be overridden in more complicated Objects, such as Key
   * Gates and the likes.
   */
  public boolean entityCanStep(Entity e) {
    return false;
  }

  /**
   * Refer to Interactable for description.
   * 
   * This method has no body as nothing should happen when the player tries to
   * stand on a normal grid.
   * This method should be overridden in more complicated Objects, such as Key
   * Gates and the likes.
   */
  public void onInteract(Entity e) {
  }

  public Sprite getSprite() {
    throw new UnsupportedOperationException("Cannot invoke on generic Grid object!");
  }
}