package test.nz.ac.vuw.ecs.swen225.gp22.fuzz;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import org.junit.jupiter.api.Test;
import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.persistence.LevelLoader;
import nz.ac.vuw.ecs.swen225.gp22.renderer.*;

/**
 * This class is for testing the ChapsChallenge game using randomly automated
 * input.
 *
 * @author Janelle Lim-Ranola - limrajane
 */
public class FuzzTest {
   private App app;
   private Game game;
   private ActionController actionController;

   /**
    * Loading the game and setting up the app.
    */
   @Test
   public void test1() {
      // start game then wait until it has started
      try {
         SwingUtilities.invokeAndWait(() -> fuzzTestGame(LevelLoader.Level1.load()));
      } catch (InterruptedException e) {
         System.out.println("Game start interrupted");
         e.printStackTrace();
      } catch (InvocationTargetException e) {
         System.out.println("Game start failed");
         e.printStackTrace();
      }
   }

   /**
    * Testing the game with randomly generated keystrokes.
    */
   @Test
   public void test2() {
   }

   public void fuzzTestGame(Level level) {
      // Starting game
      this.app = new App();
      Level firstLevel = LevelLoader.Level1.load();
      app.startLevel(firstLevel);
      this.game = Game.getInstance();
      this.actionController = app.getController();

      long t = System.currentTimeMillis();
      long end = t + 60000;
      while (System.currentTimeMillis() < end) {
         // call random methods from app with randomised input a set amount of times.
         int random = (int) (Math.random() * 4);
         int keyCode = 0;
         switch (random) {
            case 0: // up
               keyCode = 37;
               break;
            case 1: // down
               keyCode = 38;
               break;
            case 2: // left
               keyCode = 39;
               break;
            case 3: // right
               keyCode = 40;
               break;
         }
         // Pass in randomly generated keystroke into callAction method
         app.callAction(keyCode);
      }
   }
}