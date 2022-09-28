package test.nz.ac.vuw.ecs.swen225.gp22;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.persistence.LevelLoader;
import nz.ac.vuw.ecs.swen225.gp22.util.Direction;
import nz.ac.vuw.ecs.swen225.gp22.util.Time;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

public class DomainTests {
    // @Test
    // public void testLevel() {
    // Game game = new Game(LevelLoader.Level1.load());
    // Level level = game.getLevel();
    // assertEquals("Level 1", level.getTitle());
    // assertNotNull(game.getUpdateLoop());
    // assertTrue(Time.INSTANCE.isLoopRunning(Game.UPDATE_KEY));
    // }

    // @Test
    // public void testPlayerMove() {
    // Game game = new Game(LevelLoader.Level1.load());
    // Level level = game.getLevel();
    // Vector playerPos = level.getPlayer().getPosition();
    // // player starts at middle of level 1
    // // there are 2 places to move beneath the player
    // for (int i = 1; i <= 3; i++) {
    // assertTrue(level.getPlayer().tryMove(Direction.Down));
    // Vector newPos = level.getPlayer().getPosition();
    // assertTrue(newPos.x() == playerPos.x());
    // assertTrue(newPos.y() == (playerPos.y() + i),
    // "Player position is " + newPos.y() + " but should be " + (playerPos.y() +
    // i));
    // }
    // // can't move into a wall
    // Vector newPos = level.getPlayer().getPosition();
    // assertFalse(level.getPlayer().tryMove(Direction.Down));
    // assertTrue(newPos.equals(level.getPlayer().getPosition()));
    // }
}
