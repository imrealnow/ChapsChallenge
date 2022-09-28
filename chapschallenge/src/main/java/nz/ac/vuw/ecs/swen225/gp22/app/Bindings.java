package nz.ac.vuw.ecs.swen225.gp22.app;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities.Player;
import nz.ac.vuw.ecs.swen225.gp22.util.Direction;

import java.awt.event.KeyEvent;

public enum Bindings {
    Up((App, Level) -> {
        Player p = Level.getPlayer();
        p.tryMove(Direction.Up);
    }),
    Down((App, Level) -> {
        Player p = Level.getPlayer();
        p.tryMove(Direction.Down);
    }),
    Left((App, Level) -> {
        Player p = Level.getPlayer();
        p.tryMove(Direction.Left);
    }),
    Right((App, Level) -> {
        Player p = Level.getPlayer();
        p.tryMove(Direction.Right);
    });
    // Save,
    // Exit,
    // Resume;

    public static final EnumMap<Bindings, Integer> keyBindings = new EnumMap<>(Bindings.class);

    private BiConsumer<App, Level> doAction;

    Bindings(BiConsumer<App, Level> action) {
        this.doAction = action;

    }

    public void doAction(App app, Level level) {
        doAction.accept(app, level);
    }

    public static void setKeyBinding(Bindings keyBinding, int keyCode) {
        keyBindings.put(keyBinding, keyCode);
    }

    public static int getKeyBinding(Bindings keyBinding) {
        return keyBindings.get(keyBinding);
    }

    public static Bindings tryGetBindingFromKeyCode(int keyCode) {
        for (Map.Entry<Bindings, Integer> entry : keyBindings.entrySet()) {
            if (entry.getValue() == keyCode)
                return entry.getKey();
        }
        return null;
    }
}
