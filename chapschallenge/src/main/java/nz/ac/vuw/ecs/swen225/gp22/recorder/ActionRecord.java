package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.util.Direction;
import nz.ac.vuw.ecs.swen225.gp22.util.Time;

/**
 * ActionRecorder records the different moves of the different characters as well as the time that move took place
 * 
 * @author Julia Huijser - huijsejuli
 */
public class ActionRecord {
    private String action;
    private Direction direction;
    private Time time;


    /**
     * ActionRecorder constructor
     * 
     * @param a The action that took place
     * @param d The direction of the action
     * @param t The time of the action
     */
    public ActionRecord(String a, Direction d, Time t){
        setAction(a);
        setDirection(d);
        setTime(t);
    }


    public String getAction() {
        return action;
    }

    public Direction getDirection() {
        return direction;
    }

    public Time getTime() {
        return time;
    }

    public void setAction(String action) {
        this.action = action;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public void setTime(Time time) {
        this.time = time;
    }


    
}
