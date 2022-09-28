package nz.ac.vuw.ecs.swen225.gp22.util.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * A subject interface that is used to notify observers of changes
 * in subjects.
 * 
 * @param <T> The type of the subject.
 * @param <R> The type of the data that is passed to the observer.
 * @author Liam Green - greenliam
 */
public abstract class Subject<T, R> {
    // list of classes that are observing this class
    private final List<Observer<T, R>> observers = new ArrayList<>();

    /**
     * Add an observer to the list of observers.
     * 
     * @param observer
     */
    public final void addObserver(Observer<T, R> observer) {
        observers.add(observer);
    }

    /**
     * Remove an observer from the list.
     * 
     * @param observer
     */
    public final void removeObserver(Observer<T, R> observer) {
        observers.remove(observer);
    }

    /**
     * Notify all observers of a change in the subject.
     */
    @SuppressWarnings("unchecked")
    public final void notifyObservers() {
        for (Observer<T, R> observer : observers) {
            observer.notify((T) this, getObservableData());
        }
    }

    /**
     * Get the data that is passed to the observer.
     * 
     * @return The data that is passed to the observer.
     */
    public abstract R getObservableData();
}
