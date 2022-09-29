package nz.ac.vuw.ecs.swen225.gp22.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Map;
import java.util.Optional;
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

    Map<String, LoopRecord> loops = Collections.synchronizedMap(new HashMap<>());
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
            loops.get(name).timer().cancel();
        }
        Timer timer = new Timer();
        TickedTask task = new TickedTask(callback);
        timer.scheduleAtFixedRate(task, 0, 1000 / timesPerSecond);
        loops.put(name, new LoopRecord(name, timer, task));
        return timer;
    }

    /**
     * Gets the timer running the loop with the given name.
     * 
     * @param name The name of the loop.
     * @return The timer running the loop.
     */
    public Optional<TickedTask> getLoopTask(String name) {
        return Optional.ofNullable(loops.get(name)).map(LoopRecord::task);
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
            loops.get(name).timer().cancel();
            loops.remove(name);
        }
        throw new IllegalArgumentException("No loop with name " + name + " exists.");
    }

    /**
     * Stops all loops.
     */
    public void stopAll() {
        for (String name : loops.keySet()) {
            loops.get(name).timer().cancel();
        }
        loops.clear();
    }

    public int getLoopTicks(String name) {
        if (loops.containsKey(name)) {
            return loops.get(name).task().getTickCount();
        }
        throw new IllegalArgumentException("No loop with name " + name + " exists.");
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

    /**
     * Record for saving info about a loop
     */
    record LoopRecord(String key, Timer timer, TickedTask task) {
    }
}
