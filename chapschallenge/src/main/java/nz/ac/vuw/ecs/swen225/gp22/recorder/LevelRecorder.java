package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Bindings;

public class LevelRecorder {

    private LevelReplay currentLevelInProgress = new LevelReplay();

    /**
     * TODO: Add Javadoc
     * 
     * @param input
     * @param time
     * @return
     */
    public ActionRecord recordPlayerAction(Bindings input){
        ActionRecord action = new ActionRecord(input);
        currentLevelInProgress.add(action);
        return null;
    }

    /**
     * TODO: Javadoc
     */
    public void setup(){
        App.INSTANCE.getController().addObserver((controller, action) -> recordPlayerAction(action));
    }
    
}
