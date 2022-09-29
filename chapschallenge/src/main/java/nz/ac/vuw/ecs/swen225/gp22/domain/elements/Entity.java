package nz.ac.vuw.ecs.swen225.gp22.domain.elements;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

import java.util.List;
import java.util.Optional;

import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.util.Direction;

/**
 * Represents an instance that is able to move around.
 */
public abstract class Entity implements Updateable {
    private Vector position;
    private Optional<Runnable> queuedAction = Optional.empty();

    public Entity(Vector position) {
        this.position = position;
    }

    @Override
    public void update() {
        if (queuedAction.isPresent()) {
            queuedAction.get().run();
            queuedAction = Optional.empty();
        }
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector newPosition) {
        this.position = newPosition;
    }

    public boolean tryMove(Direction dir) {
        Vector newPos = getPosition().add(dir.vector());

        Tile[][] tiles = Game.getInstance().getLevel().getTiles();
        int levelHeight = tiles.length;
        int levelWidth = tiles[0].length;

        // Case 0: The player is moving to a invalid positon
        if (newPos.x() < 0 || newPos.x() >= levelWidth || newPos.y() < 0 || newPos.y() >= levelHeight) {
            throw new IndexOutOfBoundsException("Player tried to move out of bounds");
        }

        List<Interactable> interactables = Game.getInstance().getInteractablesAt(newPos);

        // if any interactables block movement, return false
        if (interactables.stream().anyMatch(i -> !i.entityCanStep(this))) {
            // can't move, so don't apply move
            // applyMove(dir, interactables);
            return false;
        }

        // otherwise, move the player
        applyMove(dir, interactables);
        return true;

    }

    protected void applyMove(Direction dir, List<Interactable> interactables) {
        queuedAction = Optional.of(() -> {
            setPosition(getPosition().add(dir.vector()));
            interactables.forEach(i -> i.onInteract(this));
        });
    }

    public Sprite getSprite() {
        throw new UnsupportedOperationException();
    }
}