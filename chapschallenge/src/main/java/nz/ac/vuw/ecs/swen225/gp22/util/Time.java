package nz.ac.vuw.ecs.swen225.gp22.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * A class that can be used to schedule tasks to loop or be run at a later time.
 * 
 * @author Liam Green - greenliam
 */
public enum Time {
    INSTANCE;

    Map<String, Timer> loops = new HashMap<>();
    Map<String, Integer> loopTimeSteps = new HashMap<>();
    Long startTime = System.currentTimeMillis();

    /**
     * Starts a loop that calls the given function a specified amount of times per
     * second.
     * 
     * @param name           The name of the loop to be used as a key.
     * @param timesPerSecond how many times per second to call the function.
     * @param callback       The function to call.
     * @return The timer object that is running the loop.
     */
    public Timer loop(String name, int timesPerSecond, Runnable callback) {
        if (loops.containsKey(name)) {
            loops.get(name).cancel();
        }
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("delayed invoke");
                callback.run();
            }
        }, 0, 1000 / timesPerSecond);
        loops.put(name, timer);
        return timer;
    }

    /**
     * Checks if a loop of a given key is currently running.
     * 
     * @param loopKey the key of the loop to check.
     * @return true if the loop is running, false otherwise.
     */
    public boolean isLoopRunning(String loopKey) {
        return loops.containsKey(loopKey);
    }

    /**
     * Stops a specified loop if it exists.
     * 
     * @param name The name of the loop to stop.
     */
    public void stop(String name) {
        if (loops.containsKey(name)) {
            loops.get(name).cancel();
            loops.remove(name);
        }
    }

    /**
     * Stops all loops.
     */
    public void stopAll() {
        for (String name : loops.keySet()) {
            loops.get(name).cancel();
        }
        loops.clear();
    }

    /**
     * Runs the callback after the specified number of milliseconds.
     * 
     * @param milliseconds the delay in milliseconds
     * @param callback     the callback to run
     */
    public void delayedInvoke(int milliseconds, Runnable callback) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                callback.run();
            }
        }, milliseconds);
    }

    /**
     * Gets the time step of a loop.
     * 
     * @param name The name of the loop.
     * @return The time step of the loop.
     *         If the loop does not exist, returns -1.
     */
    public int getLoopTimeStep(String name) {
        if (loopTimeSteps.containsKey(name)) {
            return loopTimeSteps.get(name);
        }
        return -1;
    }

    /**
     * Gets the time since the start of the game in milliseconds.
     * 
     * @return The time since the start of the game.
     */
    public long getTimeSinceStart() {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Replays a list of timed commands maintaining the delay between each command.
     * 
     * @param commands The list of commands to replay.
     */
    public void playCommandSequence(List<TimedCommand> commands) {
        Collections.sort(commands);
        TimedCommand lastCommand = commands.get(0);
        lastCommand.run();
        for (int i = 1; i < commands.size(); i++) {
            TimedCommand command = commands.get(i);
            long delay = command.getCreationTime() - lastCommand.getCreationTime();
            delayedInvoke(Math.toIntExact(delay), command);
            lastCommand = command;
        }
    }
}
