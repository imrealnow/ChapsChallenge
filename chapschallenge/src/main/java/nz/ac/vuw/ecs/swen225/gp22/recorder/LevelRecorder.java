package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.ActionController;
import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Bindings;
import nz.ac.vuw.ecs.swen225.gp22.util.Time;
import nz.ac.vuw.ecs.swen225.gp22.util.observer.Observer;

public class LevelRecorder implements Observer<ActionController, Bindings>{

    private LevelReplay currentLevelInProgress = new LevelReplay();

    /**
     * TOOD: Add Javadoc
     */
    public LevelRecorder() {
        App.INSTANCE.getController().addObserver(this);
      }

    @Override
    public void notify(ActionController t, Bindings r) {
        ActionRecord action = new ActionRecord(r, Time.INSTANCE.getTimeSinceStart();
        currentLevelInProgress.add(action);
        
    }
    
}
