package nz.ac.vuw.ecs.swen225.gp22.persistence;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.dom4j.DocumentException;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;

/**
 * Class used for loading a level from an XML file.
 * 
 * @author Liam Green - greenliam
 */
public enum LevelLoader {
    Level1("levels/level1.xml");

    LevelLoader(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    private String resourcePath;
    private static LevelFactory levelFactory = new LevelFactory();
    private static Map<Integer, LevelLoader> loadedLevels;

    /**
     * Loads a predefined game level.
     * 
     * @return the level loaded from the XML file
     * @throws DocumentException if the file could not be loaded
     */
    public Level load() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(resourcePath);
            if (inputStream == null) {
                throw new RuntimeException("Could not find resource: " + resourcePath);
            }
            return levelFactory.createFromXML(inputStream);
        } catch (DocumentException e) {
            System.out.println("Invalid level XML file: " + resourcePath);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads all predefined game levels.
     * 
     * @return a copy of the set of levels loaded from the XML files
     */
    public static Set<Level> loadAll() {
        if (loadedLevels == null) {
            loadedLevels = new HashMap<>();
            for (LevelLoader levelLoader : LevelLoader.values()) {
                loadedLevels.put(levelLoader.load().getLevelID(), levelLoader);
            }
        }
        return loadedLevels.values().stream().map(LevelLoader::load).collect(HashSet::new,
                Set::add, Set::addAll);
    }

    /**
     * Loads a level using its index
     * 
     * @param index the index of the level to load
     * @return the level loaded from the XML file
     */
    public static Level getLevel(int index) {
        if (loadedLevels == null) {
            loadAll();
        }
        LevelLoader levelLoader = loadedLevels.get(index);
        if (levelLoader == null) {
            throw new RuntimeException("Could not find level: " + index);
        }
        return levelLoader.load();
    }
}
