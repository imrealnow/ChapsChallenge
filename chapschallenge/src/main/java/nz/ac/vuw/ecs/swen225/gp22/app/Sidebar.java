package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Item;

public class Sidebar extends JPanel {
    private App app;
    private Level level;
    private SideLabel time;
    private SideLabel currentLevel;
    private SideLabel keysCollected;
    private SideLabel friendsLeft;
    private HashMap<Item, Integer> inventory;
    private int friends;
    private int friendsCollected;
    private int keys;

    Sidebar(Level level, App app) {
        // Set fields
        super();
        this.app = app;
        this.level = level;
        friends = level.getFriendsNeeded();
        inventory = level.getPlayer().inventory();
        // Set size
        setPreferredSize(new Dimension(200, 600));
        setMinimumSize(getPreferredSize());
        // Create Labels
        time = new SideLabel("Time Left: " + level.getTimeLimit());
        currentLevel = new SideLabel(level.getTitle());
        keysCollected = new SideLabel("Keys: " + inventory.size());
        friendsLeft = new SideLabel("Friends Left: " + friends);
        // Set the layout and add labels
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(time);
        add(currentLevel);
        add(keysCollected);
        add(friendsLeft);
    }

    /**
     * Updates the text of sidebar labels
     */
    public void updateText() {
        time.setText("Time Left: " + app.getTime());
        friendsLeft.setText("Friends Left: " + getFriendsLeft());
    }

    /**
     * Loops through players inventory till it finds friends then finds out how many
     * have been collected
     * 
     * @return amount of friends still left to collect
     */
    private int getFriendsLeft() {
        for (Map.Entry<Item, Integer> entry : inventory.entrySet()) {
            if (entry.getKey() == Item.ItemFriend) {
                friendsCollected = entry.getValue();
            }
        }
        return friends - friendsCollected;
    }
}
