package nz.ac.vuw.ecs.swen225.gp22.persistence;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Pickup;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities.Player;
import nz.ac.vuw.ecs.swen225.gp22.persistence.factories.entities.PickupElementFactory;
import nz.ac.vuw.ecs.swen225.gp22.persistence.factories.entities.PlayerElementFactory;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

/**
 * Registry for entity factories. Entity factories can register themselves to
 * this class,
 * allowing for matching of entity types to their corresponding factory.
 * 
 * @author Liam Green - greenliam
 */
public class EntityElementFactoryRegistry {
    private static Map<Class<? extends Entity>, Supplier<ElementFactory<Entity>>> factories = new HashMap<>();
    static {
        factories.put(Player.class, (Supplier<ElementFactory<Entity>>) () -> new PlayerElementFactory());
        factories.put(Pickup.class, (Supplier<ElementFactory<Entity>>) () -> new PickupElementFactory());
    }

    public static ElementFactory<Entity> getFactory(Class<? extends Entity> clazz) {
        if (!factories.containsKey(clazz)) {
            EntityElementFactoryRegistry wrapper = new EntityElementFactoryRegistry();
            return wrapper.new DefaultEntityFactory();
        }
        return factories.get(clazz).get();
    }

    public static void registerFactory(Class<? extends Entity> clazz,
            Supplier<ElementFactory<Entity>> factory) {
        factories.put(clazz, factory);
    }

    /**
     * Get the class of the entity from the entity's element.
     * 
     * @param element the element to get the class from
     * @return the class of the entity
     */
    @SuppressWarnings("unchecked")
    public static Class<? extends Entity> getClassFromElement(Element element) {
        String className = element.attributeValue("type");
        try {
            return (Class<? extends Entity>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + className);
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.out.println("Class " + className + " is not an entity");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Element factory for entities. Tries to create an entity from the given
     * element, using the entity's class name to invoke a constructor.
     * 
     * @author Liam Green - greenliam
     */
    class DefaultEntityFactory implements ElementFactory<Entity> {
        @Override
        public Element createElement(Entity entity) {
            Element entityElement = DocumentHelper.createElement("Entity");
            Vector pos = entity.getPosition();
            entityElement.addAttribute("x", Double.toString(pos.x()));
            entityElement.addAttribute("y", Double.toString(pos.y()));
            entityElement.addAttribute("type", entity.getClass().getName());
            return entityElement;
        }

        @Override
        public Entity createFromElement(Element element) {
            double x = Double.parseDouble(element.attributeValue("x"));
            double y = Double.parseDouble(element.attributeValue("y"));
            String type = element.attributeValue("type");
            Vector pos = new Vector(x, y);
            try {
                Class<? extends Entity> clazz = EntityElementFactoryRegistry.getClassFromElement(element);
                Constructor<? extends Entity> constructor = clazz.getConstructor(Vector.class);
                return constructor.newInstance(pos);
            } catch (NoSuchMethodException e) {
                System.out.println("No constructor that takes a vector found for entity type " + type);
                e.printStackTrace();
            } catch (InstantiationException e) {
                System.out.println("Could not instantiate entity of type " + type);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                System.out.println("Could not access constructor for entity of type " + type);
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                System.out.println("Could not invoke constructor for entity of type " + type);
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                System.out.println("Could not invoke constructor for entity of type " + type);
                e.printStackTrace();
            }
            return null;
        }
    }
}
