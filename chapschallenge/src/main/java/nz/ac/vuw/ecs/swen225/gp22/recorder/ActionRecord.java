package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nz.ac.vuw.ecs.swen225.gp22.app.ActionController;
import nz.ac.vuw.ecs.swen225.gp22.app.Bindings;
import nz.ac.vuw.ecs.swen225.gp22.util.Direction;
import nz.ac.vuw.ecs.swen225.gp22.util.Time;

/**
 * ActionRecord records the move of the character as well as the time that move
 * took place
 * 
 * @author Julia Huijser - huijsejuli
 */
public class ActionRecord {
    private static final Map<String, Bindings> actionBinding = new HashMap<String, Bindings>() {
        {
            put("PlayerUp", Bindings.Up);
            put("PlayerDown", Bindings.Down);
            put("PlayerLeft", Bindings.Left);
            put("PlayerRight", Bindings.Right);
        }
    };
    private int time;
    private Bindings action;

    /**
     * TO-DO: fill javadoc
     * 
     * @param action
     * @param time
     */
    public ActionRecord(Bindings action, int time){
        this.action = action;
        this.time = time;
    }

    /**
     * TODO: Fill javadoc
     * 
     * @param actionString
     * @param time
     */
    public ActionRecord(String actionString, int time){
        Bindings actionCurrent = actionBinding.get(actionString);
        if(action == null){
            throw new IllegalArgumentException("Invalid action key");
        }
        this.action = actionCurrent;
        this.time = time;
    }
    
}
