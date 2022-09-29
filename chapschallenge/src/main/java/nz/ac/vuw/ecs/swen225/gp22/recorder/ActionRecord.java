package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
    private Long time = Time.INSTANCE.getTimeSinceStart();
    private Bindings action;

    /**
     * TO-DO: fill javadoc
     * 
     * @param action
     * @param time
     */
    public ActionRecord(Bindings action, Long time){
        this.action = action;
        this.time = time;
    }

    /**
     * TODO: Fill javadoc
     * 
     * @param actionString
     * @param time
     */
    public ActionRecord(String actionString, Long time){
        Bindings actionCurrent = actionBinding.get(actionString);
        if(action == null){
            throw new IllegalArgumentException("Invalid action key");
        }
        this.action = actionCurrent;
        this.time = time;
    }

    /**
     * TODO - fill javadoc
     * 
     * @param action
     * @return
     */
    public static String getActionKey(Bindings action){
        for(Entry<String, Bindings> entry : actionBinding.entrySet()){
            if(entry.getValue().equals(action)){
                return entry.getValue().name();
            } else {
                throw new IllegalArgumentException(action.name() + " is not a valid action");
            }
        }
        return null;
    }  
    
    /**
     * TODO: Fill javadoc
     * 
     * @return
     */
    public Bindings getAction(){
        return action;
    }

    /**
     * TODO: Fill javadoc
     * 
     * @return
     */
    public Long getTime(){
        return time;
    }

}
