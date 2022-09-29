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
import nz.ac.vuw.ecs.swen225.gp22.util.Time;

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
    private StartScreen startScreen;
    private GameView gameView;
    private Sidebar sidebar;
    private int levelTime;

    public App() {
        // Set title
        super("Chaps Challenge");
        // Assertions
        assert SwingUtilities.isEventDispatchThread();
        assert INSTANCE == null : "App instance already exists";
        // Setup JFrame
        // setJMenuBar(createMenuBar());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultBindings();
        // Setup instance
        actionController = new ActionController();
        game = new Game();
        INSTANCE = this;
        // Setup UI
        startScreen = new StartScreen();
        add(startScreen);
        setVisible(true);
        pack();
    }

    /*
     * Sets bindings for all movement keys.
     */
    private void setDefaultBindings() {
        Bindings.setKeyBinding(Bindings.Up, KeyEvent.VK_UP);
        Bindings.setKeyBinding(Bindings.Down, KeyEvent.VK_DOWN);
        Bindings.setKeyBinding(Bindings.Left, KeyEvent.VK_LEFT);
        Bindings.setKeyBinding(Bindings.Right, KeyEvent.VK_RIGHT);
    }

    public ActionController getController() {
        return actionController;
    }

    /*
     * Calls the action associated with the keycode
     */
    public void callAction(int keyCode) {
        actionController.tryExcecutefromKeyCode(keyCode, this, game.getLevel());
    }

    /**
     * Creates and starts the selected level
     * 
     * @param selectedLevel the Level that has been selected by the player
     */
    public void startLevel(Level selectedLevel) {
        // Clear screen and set level
        remove(startScreen);
        game.setLevel(selectedLevel);
        levelTime = selectedLevel.getTimeLimit();
        Time.INSTANCE.loop("Level Time", 1, () -> changeTime(-1));
        // Add Keylistener and make focusable
        addKeyListener(this);
        requestFocusInWindow();
        // Setup UI
        setSize(new Dimension(800, 600));
        setMinimumSize(getSize());
        gameView = new GameView(selectedLevel);
        gameView.setMinimumSize(new Dimension(600, 600));
        getContentPane().add((gameView));
        getContentPane().setBackground(Color.darkGray);
        sidebar = new Sidebar(selectedLevel, this);
        add(sidebar, BorderLayout.EAST);
        setVisible(true);
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

    public void changeTime(int toChange) {
        levelTime += toChange;
        if (sidebar != null) {
            sidebar.updateText();
            repaint();
        }
    }

    public int getTime() {
        return levelTime;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        callAction(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
