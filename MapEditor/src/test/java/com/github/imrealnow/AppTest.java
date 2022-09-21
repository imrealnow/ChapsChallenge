package com.github.imrealnow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    void testLevelXMLFactory() {
        try {
            Level level = new Level(10, 10, "Test level", "Test level description");
            String levelTileGrid = level.map().toString();
            LevelXMLFactory factory = new LevelXMLFactory(level);
            File xml = factory.toXML("test");
            Level level2 = factory.fromXML(xml);
            assertEquals(level.name(), level2.name(), "Level names should be the same");
            assertEquals(level.description(), level2.description(), "Level descriptions should be the same");
            assertEquals(levelTileGrid, level2.map().toString(), "Tile grids should be equal");
        } catch (Exception e) {
            fail();
        }
    }
}
