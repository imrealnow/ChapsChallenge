package nz.ac.vuw.ecs.swen225.gp22.persistence;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;

public class TileElementFactoryRegistry {
    private static Map<Class<? extends Tile>, Supplier<ElementFactory<Tile>>> factories = new HashMap<>();

    public static ElementFactory<Tile> getFactory(Class<? extends Tile> clazz) {
        if (!factories.containsKey(clazz)) {
            TileElementFactoryRegistry wrapper = new TileElementFactoryRegistry();
            return wrapper.new DefaultTileFactory();
        }
        return factories.get(clazz).get();
    }

    public static void registerFactory(Class<? extends Tile> clazz,
            Supplier<ElementFactory<Tile>> factory) {
        factories.put(clazz, factory);
    }

    /**
     * Get the class of the tile from the tile's element.
     * 
     * @param element the element to get the class from
     * @return the class of the tile
     */
    @SuppressWarnings("unchecked")
    public static Class<Tile> getClassFromElement(Element element) {
        String className = element.attributeValue("type");
        if (className == null) {
            throw new IllegalArgumentException("Element has no type attribute");
        }
        try {
            return (Class<Tile>) Class
                    .forName("nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids." + className);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + className);
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.out.println("Class " + className + " is not an tile");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Element factory for tiles. Tries to create a tile from the given
     * element, using the tile's class name to invoke a constructor.
     * 
     * @author Liam Green - greenliam
     */
    class DefaultTileFactory implements ElementFactory<Tile> {
        @Override
        public Element createElement(Tile tile) {
            Element tileElement = DocumentHelper.createElement("Tile");
            System.out.println(tile.getClass().getSimpleName());
            tileElement.addAttribute("type", tile.getClass().getSimpleName());
            return tileElement;
        }

        @Override
        public Tile createFromElement(Element element) {
            String type = element.attributeValue("type");
            try {
                Class<? extends Tile> clazz = TileElementFactoryRegistry.getClassFromElement(element);
                Constructor<? extends Tile> constructor = clazz.getConstructor();
                return constructor.newInstance();
            } catch (NoSuchMethodException e) {
                System.out.println("No constructor for tile type " + type);
                e.printStackTrace();
            } catch (InstantiationException e) {
                System.out.println("Could not instantiate tile of type " + type);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                System.out.println("Could not access constructor for tile of type " + type);
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                System.out.println("Could not invoke constructor for tile of type " + type);
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                System.out.println("Could not invoke constructor for tile of type " + type);
                e.printStackTrace();
            }
            return null;
        }
    }
}
