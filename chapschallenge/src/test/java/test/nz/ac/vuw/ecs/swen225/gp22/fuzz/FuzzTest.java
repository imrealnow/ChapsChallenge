package test.nz.ac.vuw.ecs.swen225.gp22.fuzz;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import org.junit.jupiter.api.Test;
import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.persistence.LevelLoader;

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
    * Loading the game and setting up the app. Testing the game's level 1 with
    * randomly generated keystrokes.
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
    * Testing the game's level 2 with randomly generated keystrokes.
    */
   @Test
   public void test2() {
      // start game then wait until it has started
      // try {
      //    SwingUtilities.invokeAndWait(() -> fuzzTestGame(LevelLoader.Level2.load()));
      // } catch (InterruptedException e) {
      //    System.out.println("Game start interrupted");
      //    e.printStackTrace();
      // } catch (InvocationTargetException e) {
      //    System.out.println("Game start failed");
      //    e.printStackTrace();
      // }
   }

   public void fuzzTestGame(Level level) {
      // Starting game
      this.app = new App();
      // Level firstLevel = LevelLoader.Level1.load();
      app.startLevel(level);
      this.game = Game.getInstance();
      this.actionController = app.getController();

      // get system's current time in milliseconds and add 60000 milliseconds (60
      // seconds) to it
      long systemTime = System.currentTimeMillis();
      long endTime = systemTime + 60000;
      while (System.currentTimeMillis() < endTime) {
         // Generate random number between 0 and 4
         int random = (int) (Math.random() * 4);
         int keyCode = 0;
         // setting keycode up, down, left or right depending on random number
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
         // Slowing down keystrokes so it's easier to see - delete later
         try {
            Thread.sleep(10);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
}