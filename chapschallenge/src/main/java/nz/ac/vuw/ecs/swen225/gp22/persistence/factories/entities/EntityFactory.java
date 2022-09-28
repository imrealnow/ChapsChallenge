package nz.ac.vuw.ecs.swen225.gp22.persistence.factories.entities;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Pickup;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

public class EntityFactory {
    public static Entity create(Entity reference, Vector position) {
        if (reference instanceof Pickup pickup) {
            return new Pickup(position, pickup.getItem());
        }
        Class<? extends Entity> clazz = reference.getClass();
        try {
            return clazz.getConstructor(Vector.class).newInstance(position);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
