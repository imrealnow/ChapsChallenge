package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.HashMap;
import java.util.Timer;

/**
 * Represents the Game object, which contains the current level and various
 * stats related to the game.
 */
public class Game {
    private Level currentLevel;
    private HashMap<String,Timer> loops = new HashMap<>();

    /**
     * Called every frame, causing all of the relevant game elements to call their
     * update methods.
     */
    public void update() {
        currentLevel.getEntities().forEach(entity -> entity.update());
        loops.values().forEach(l -> {/* do nothing?? */});
    }

    /**
     * Adds a task to the Games set of loops.
     * 
     * @param key The name of the task
     * @param action The task to be be performed
     * @param timesPerSecond The frequency of this task
     */
    public void createLoop(String key, TimerTask action, int timesPerSecond){
        Timer loop = new Timer();
        loop.scheduleAtFixedRate(action, 0, 1000/timesPerSecond);
        loops.put(key,loop);
    }
    /**
    * Returns the current level.
    *  
    * @return The currentl game level.
    */
    public Level getCurrentLevel(){
        return currentLevel;
    }

    public List<Interactable> getInteractablesAt(Vector pos){
        List <Interactable> is = new ArrayList<Interactable>();
        
        //TODO: Finish this.
        //I will implement this later.
        //Recuse through level entities and grids, returnin all interactables at 
        //(or around????) the specified position.
        return is;
    }
}