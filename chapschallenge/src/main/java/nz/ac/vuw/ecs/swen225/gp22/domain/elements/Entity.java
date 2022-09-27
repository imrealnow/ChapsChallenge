package nz.ac.vuw.ecs.swen225.gp22.domain.elements;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;
import nz.ac.vuw.ecs.swen225.gp22.util.Direction;

/**
 * Represents an instance that is able to move around.
 */
public abstract class Entity implements Updateable {
    private Vector position;

    public Entity(Vector position) {
        this.position = position;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public abstract boolean tryMove(Direction dir);

    public Sprite getSprite() {
        throw new UnsupportedOperationException();
    }
}