package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.util.observer.Subject;

public class ActionController extends Subject<ActionController, Bindings> {
    private Bindings lastActionExecuted;

    public void tryExcecutefromKeyCode(int keyCode, App app, Level level) {
        Bindings action = Bindings.tryGetBindingFromKeyCode(keyCode);
        if (action != null) {
            executeAction(action, app, level);
        }
    }

    public void executeAction(Bindings action, App app, Level currentLevel) {
        action.doAction(app, currentLevel);
        lastActionExecuted = action;
        notifyObservers();
    }

    @Override
    public Bindings getObservableData() {
        return lastActionExecuted;
    }

}
