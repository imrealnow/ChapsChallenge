package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

/**
 * Class used for setting up the application interface
 *
 * @author Jack Harrisson - harrisjack1
 */
public class App extends JFrame {
    public static App INSTANCE;
    Level selectedLevel;

    App() {
        assert SwingUtilities.isEventDispatchThread();
        assert INSTANCE == null : "App instance already exists";
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startScreen();
        setJMenuBar(createMenuBar());
        setVisible(true);
        INSTANCE = this;
    }

    /**
     * Creates start screen GUI. Currently has
     * start button and buttons to select levels
     */
    private void startScreen() {
        var welcome = new JLabel("Welcome to ChapsChallenge!");
        var start = new JButton("Start!");
        var Level1 = new JButton("Level 1");
        var Level2 = new JButton("Level 2");
        add(BorderLayout.CENTER, welcome);
        add(BorderLayout.SOUTH, start);
        JPanel panel = new JPanel();
        panel.add(Level1);
        panel.add(Level2);
        add(panel, BoxLayout.X_AXIS);
        Level1.addActionListener(e -> {// Set selectedLevel to level1
            Level1.setBackground(Color.GREEN);
            Level2.setBackground(Color.RED);
        });

        Level2.addActionListener(e -> {// Set selectedLevel to level2
            Level2.setBackground(Color.GREEN);
            Level1.setBackground(Color.RED);
        });
        setPreferredSize(new Dimension(800, 400));
        pack();
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createMenu());
        return menuBar;
    }

    /*
     * Creates a basic JMenu with some options
     */
    private JMenu createMenu() {
        JMenu menu = new JMenu("Options");
        menu.add(new JMenuItem() {
            {
                setText("Save");
            }
        });

        menu.add(new JMenuItem() {
            {
                setText("Load");
            }
        });

        menu.add(new JMenuItem() {
            {
                setText("Restart");
            }
        });
        return menu;
    }
}
