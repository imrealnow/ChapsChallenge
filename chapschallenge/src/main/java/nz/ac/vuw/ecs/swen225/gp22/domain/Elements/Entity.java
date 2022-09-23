package nz.ac.vuw.ecs.swen225.gp22.domain.elements;
import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;
import nz.ac.vuw.ecs.swen225.gp22.util.Direction;

/**
 * Represents an instance that is able to move around.
 */
public interface Entity extends Updateable {
    public boolean tryMove(Direction dir);

    public Sprite getSprite();

    public Vector getPosition();
}