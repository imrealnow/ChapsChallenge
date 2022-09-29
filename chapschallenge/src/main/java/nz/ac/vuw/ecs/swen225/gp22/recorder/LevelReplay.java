package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.util.ArrayList;
import java.util.List;

import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.persistence.LevelLoader;

/**
 * LevelReplay saves the specific level for replay
 * 
 * @author Julia Huijser - huijsejuli
 */
public class LevelReplay {

    private List<ActionRecord> actions;
    private int levelIndex;

    /**
    * Level Replay Constructor
    * 
    * @param levelIndex the int of the levels index to know which level to replay
    * @param actions List of the actions that took place during that play to replay
    */
    public LevelReplay(int levelIndex, List<ActionRecord> actions) {
        this.levelIndex = Game.getInstance().getLevel().getLevelID();
        this.actions = actions;
    }

    /**
     * Blank Level Replay Constructor
     */
    public LevelReplay() {
        this.levelIndex = Game.getInstance().getLevel().getLevelID();
        this.actions = new ArrayList<>();
    }

    /**
     * Add ActionRecord to the list of actions
     * 
     * @param a an Action Record 
     */
    public void add(ActionRecord a) {
        actions.add(a);
    }

    /**
     * Getter for the list of ActionRecords to get the actions of the entity
     * @return
     */
    public Iterable<ActionRecord> getActions() {
        return actions;
    }

    /**
     * Getter for the level index to know which level we're on
     * @return
     */
    public int getLevelIndex() {
        return levelIndex;
    }
}
