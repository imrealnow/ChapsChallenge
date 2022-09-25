package test.nz.ac.vuw.ecs.swen225.gp22;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;

public class SpriteTest {
    @Test
    public void testSprite() {
        Sprite sprite = Sprite.TileGrass;
        assertNotNull(sprite.sprite);
    }
}
