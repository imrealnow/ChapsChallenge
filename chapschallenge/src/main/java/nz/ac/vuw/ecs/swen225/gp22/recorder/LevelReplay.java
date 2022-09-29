package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.util.ArrayList;
import java.util.List;

/**
 * LevelReplay saves the specific level for replay
 * 
 * @author Julia Huijser - huijsejuli
 */
public class LevelReplay {

    private List<ActionRecord> actions = new ArrayList<ActionRecord>();
    
    public LevelReplay(Level level)

    /**
     * Add ActionRecord to the list of actions
     * 
     * @param a
     */
    public void add(ActionRecord a){
        actions.add(a);
    }
    
}
