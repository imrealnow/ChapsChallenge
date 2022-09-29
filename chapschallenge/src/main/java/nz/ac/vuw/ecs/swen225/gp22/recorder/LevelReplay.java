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

    public LevelReplay(int levelIndex, List<ActionRecord> actions) {
        this.levelIndex = LevelLoader.getLevelIndex(Game.getInstance().getLevel());
        this.actions = actions;
    }

    // blank constructor for making an new file
    public LevelReplay() {
        this.levelIndex = LevelLoader.getLevelIndex(Game.getInstance().getLevel());
        this.actions = new ArrayList<>();
    }

    /**
     * Add ActionRecord to the list of actions
     * 
     * @param a
     */
    public void add(ActionRecord a) {
        actions.add(a);
    }

    public Iterable<ActionRecord> getActions() {
        return actions;
    }

    public int getLevelIndex() {
        return levelIndex;
    }
}
