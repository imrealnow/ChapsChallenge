package nz.ac.vuw.ecs.swen225.gp22.domain.Elements;

import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

/**
 * Represents an instance that is able to move around.
 */
public interface Entity extends Updateable {
    public boolean tryMove(Vector newPos, Game game);
    
    public BufferedImage getSprite();

    public Vector getPosition();
}