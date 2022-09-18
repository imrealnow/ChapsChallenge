package nz.ac.vuw.ecs.swen225.gp22.util;

/**
 * A command that can be executed after a certain amount of time has passed.
 * 
 * @author Liam Green - greenliam
 */
public class TimedCommand implements Runnable, Comparable<TimedCommand> {
    private final Runnable command;
    private long creationTime;

    public TimedCommand(Runnable command) {
        this.command = command;
        this.creationTime = Time.INSTANCE.getTimeSinceStart();
    }

    /**
     * Gets the time the command was created at since the start of the program.
     * 
     * @return The time the command was created at.
     */
    public long getCreationTime() {
        return creationTime;
    }

    @Override
    public void run() {
        command.run();
    }

    @Override
    public int compareTo(TimedCommand o) {
        return Long.compare(creationTime, o.creationTime);
    }
}
