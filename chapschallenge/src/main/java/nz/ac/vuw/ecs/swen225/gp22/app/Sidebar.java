package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;

public class Sidebar extends JPanel {
    private App app;
    private Level level;
    private SideLabel time;
    private SideLabel currentLevel;
    private SideLabel keysCollected;
    private SideLabel friendsLeft;

    Sidebar(Level level, App app) {
        super();
        this.app = app;
        this.level = level;
        setPreferredSize(new Dimension(200, 600));
        setMinimumSize(getPreferredSize());

        time = new SideLabel("Time Left: " + level.getTimeLimit());
        currentLevel = new SideLabel(level.getTitle());
        keysCollected = new SideLabel("Keys: ");
        friendsLeft = new SideLabel("Friends Left: " + level.getFriendsNeeded());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(time);
        add(currentLevel);
        add(keysCollected);
        add(friendsLeft);
    }

    public void updateText() {
        time.setText("Time Left: " + app.getTime());
    }
}
