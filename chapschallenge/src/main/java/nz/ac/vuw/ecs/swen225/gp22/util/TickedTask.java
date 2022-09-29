package nz.ac.vuw.ecs.swen225.gp22.util;

import java.util.Optional;
import java.util.TimerTask;

/**
 * Extension of timer task that keeps track of how many times it has been run.
 */
public class TickedTask extends TimerTask {
    private int tickCount = 0;
    private Runnable callback;
    private Optional<Runnable> postCallback = Optional.empty();

    public TickedTask(Runnable callback) {
        this.callback = callback;
    }

    @Override
    public void run() {
        tickCount++;
        callback.run();
        postCallback.ifPresent(action -> action.run());
    }

    public int getTickCount() {
        return tickCount;
    }

    public void setPostCallback(Runnable callback) {
        postCallback = Optional.of(callback);
    }

    public void clearPostCallback() {
        postCallback = Optional.empty();
    }
}
