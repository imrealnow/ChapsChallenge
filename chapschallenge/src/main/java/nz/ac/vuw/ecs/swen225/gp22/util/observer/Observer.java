package nz.ac.vuw.ecs.swen225.gp22.util.observer;

/**
 * An observer interface that is used to notify observers of changes
 * in subjects.
 * 
 * @param <T> The type of the subject.
 * @param <R> The type of the data that is passed to the observer.
 * @author Liam Green - greenliam
 */
public interface Observer<T, R> {
    public void notify(T t, R r);
}
