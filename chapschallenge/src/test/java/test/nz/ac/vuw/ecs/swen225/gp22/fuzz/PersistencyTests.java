package test.nz.ac.vuw.ecs.swen225.gp22.fuzz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.junit.jupiter.*;
import org.junit.jupiter.api.Test;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Objects.Grids.GridFence;
import nz.ac.vuw.ecs.swen225.gp22.persistence.LevelFactory;

public class PersistencyTests {
    @Test
    public void testLevelSerialization() {
        Tile[][] tiles = new Tile[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j] = new GridFence();
            }
        }
        List<Entity> entities = new ArrayList<Entity>();

        Level level = new Level(tiles, entities, "test", 10);
        LevelFactory factory = new LevelFactory();
        Level level2 = null;
        try {
            File levelXML = factory.createXML("test.xml", level);
            level2 = factory.createFromXML(levelXML);
        } catch (IOException e) {
            fail("IOException thrown");
        } catch (DocumentException e) {
            fail("DocumentException thrown");
        }
        assertEquals(level, level2);
    }
}
