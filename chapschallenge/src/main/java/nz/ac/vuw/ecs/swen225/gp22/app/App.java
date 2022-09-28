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

import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.persistence.LevelLoader;
import nz.ac.vuw.ecs.swen225.gp22.renderer.GameView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class used for setting up the application interface
 *
 * @author Jack Harrisson - harrisjack1
 */
public class App extends JFrame implements KeyListener {
    public static App INSTANCE;
    private ActionController actionController;
    private Game game;
    private Runnable closeStart = () -> {
    };

    public App() {
        assert SwingUtilities.isEventDispatchThread();
        assert INSTANCE == null : "App instance already exists";
        actionController = new ActionController();
        game = new Game();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startScreen();
        setJMenuBar(createMenuBar());
        setVisible(true);
        INSTANCE = this;
    }

    public ActionController getController() {
        return actionController;
    }

    public void callAction(int keyCode) {
        actionController.tryExcecutefromKeyCode(keyCode, this, game.getLevel());
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

        Bindings.setKeyBinding(Bindings.Up, KeyEvent.VK_UP);
        Bindings.setKeyBinding(Bindings.Down, KeyEvent.VK_DOWN);
        Bindings.setKeyBinding(Bindings.Left, KeyEvent.VK_LEFT);
        Bindings.setKeyBinding(Bindings.Right, KeyEvent.VK_RIGHT);

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

        start.addActionListener(e -> {
            startLevel(LevelLoader.Level1.load());
        });
        closeStart.run();
        closeStart = () -> {
            remove(start);
            remove(panel);
            panel.remove(Level1);
            panel.remove(Level2);
            repaint();
        };
        setPreferredSize(new Dimension(800, 400));
        pack();
    }

    public void startLevel(Level selectedLevel) {
        closeStart.run();
        game.setLevel(selectedLevel);
        GameView level1 = new GameView(game.getLevel());

        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        add(level1);
        pack();
        setVisible(true);
        JPanel sidePanel = new JPanel();
        JLabel level = new JLabel() {
            {
                setText("LEVEL");
            }
        };

        JLabel Time = new JLabel() {
            {
                setText("TIME");
            }
        };

        JLabel Chips = new JLabel() {
            {
                setText("CHIPS LEFT");
            }
        };

        sidePanel.add(level, BoxLayout.X_AXIS);
        sidePanel.add(Time, BoxLayout.X_AXIS);
        sidePanel.add(Chips, BoxLayout.X_AXIS);
        add(BorderLayout.EAST, sidePanel);
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

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.print("Key Pressed: " + e.getKeyCode());
        callAction(e.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
