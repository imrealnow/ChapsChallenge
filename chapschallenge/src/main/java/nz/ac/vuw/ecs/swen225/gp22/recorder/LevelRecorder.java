package nz.ac.vuw.ecs.swen225.gp22.recorder;


import java.io.IOException;

import nz.ac.vuw.ecs.swen225.gp22.app.ActionController;
import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Bindings;
import nz.ac.vuw.ecs.swen225.gp22.util.Time;
import nz.ac.vuw.ecs.swen225.gp22.util.observer.Observer;

/**
 * LevelRecorder records the level and saves the current replay of that level to an
 * XML file
 * 
 * @author Julia Huijser - huijsejuli
 */
public class LevelRecorder implements Observer<ActionController, Bindings> {
    private static final LevelReplayFactory recordingFactory = new LevelReplayFactory();

    private LevelReplay currentLevelInProgress = new LevelReplay();

    /**
     * LevelRecorder constructor 
     */
    public LevelRecorder() {
        App.INSTANCE.getController().addObserver(this);
    }

    /**
     * Notify notifies the observer of changes in the subject
     */
    @Override
    public void notify(ActionController t, Bindings r) {
        ActionRecord action = new ActionRecord(r, Time.INSTANCE.getTimeSinceStart());
        currentLevelInProgress.add(action);
    }

    /**
     * SaveCurrentReplay saves the current replay into an XML file
     */
    public void saveCurrentReplay(String path) throws IOException {
        try {
            recordingFactory.createXML(path, currentLevelInProgress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
