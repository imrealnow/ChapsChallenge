package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;

public class Sidebar extends JPanel {

    Sidebar(Level level) {
        super();
        // setBackground(Color.darkGray);
        setPreferredSize(new Dimension(200, 600));
        setMinimumSize(getPreferredSize());

        SideLabel time = new SideLabel("Time Left: " + level.getTimeLimit());
        SideLabel currentLevel = new SideLabel(level.getTitle());
        SideLabel keysCollected = new SideLabel("Keys: ");
        SideLabel friendsLeft = new SideLabel("Friends Left: " + level.getFriendsNeeded());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(time);
        add(currentLevel);
        add(keysCollected);
        add(friendsLeft);
    }
}
