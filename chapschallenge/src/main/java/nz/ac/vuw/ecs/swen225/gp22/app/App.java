package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * Class used for setting up the application interface
 *
 * @author Jack Harrisson - harrisjack1
 */
public class App extends JFrame {
    public static App INSTANCE;

    App() {
        assert SwingUtilities.isEventDispatchThread();
        assert INSTANCE == null : "App instance already exists";
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startScreen();
        setVisible(true);
        INSTANCE = this;
    }

    private void startScreen() {
        var welcome = new JLabel("Welcome to ChapsChallenge!");
        var start = new JButton("Start!");
        add(BorderLayout.CENTER, welcome);
        add(BorderLayout.SOUTH, start);
        setPreferredSize(new Dimension(800, 400));
        pack();
    }
}
