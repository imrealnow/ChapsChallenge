package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.Bindings;

public class LevelRecorder {

    private LevelReplay currentLevelInProgress = new LevelReplay();

    public ActionRecord recordPlayerAction(Bindings input, int time){
        ActionRecord action = new ActionRecord(input, time);
        currentLevelInProgress.add(action);
        return null;
    }
    
}
