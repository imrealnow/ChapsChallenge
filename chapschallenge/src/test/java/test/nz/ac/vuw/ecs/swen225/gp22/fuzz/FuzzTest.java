package test.nz.ac.vuw.ecs.swen225.gp22.fuzz;

import org.junit.jupiter.api.Test;
import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.renderer.*;
import nz.ac.vuw.ecs.swen225.gp22.persistence.*;

/**
 * This class is for randomly generating test cases for the ChapsChallenge game.
 *
 * @author Janelle Lim-Ranola - limrajane
 */
public class FuzzTest {
     /**
     * Testing the game with randomly generated keystrokes.
     */
    @Test
    public void test1() {
       //load Level1 as a Level object
       Level currentLevel = LevelLoader.Level1.load();

       //Create new game object and set the current level to Level1
       Game game = new Game(currentLevel);
       //GameView game = new GameView(LevelLoader.Level1.load());
       
       //call random methods from app with randomised input a set amount of times.



    }

     /**
     * 
     */
    @Test
    public void test2() {

    }
}
