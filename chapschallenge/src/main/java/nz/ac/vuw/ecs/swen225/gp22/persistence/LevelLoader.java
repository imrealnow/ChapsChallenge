package nz.ac.vuw.ecs.swen225.gp22.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;

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
}
