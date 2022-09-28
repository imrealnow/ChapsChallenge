package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.List;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.persistence.LevelLoader;

public class StartScreen extends JPanel {
    private static int PADDING = 50;

    public StartScreen() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(getPreferredSize());
        // header
        JLabel header = new JLabel("Chaps Challenge");
        header.setHorizontalTextPosition(JLabel.CENTER);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setFont(header.getFont().deriveFont(32f));
        JLabel subHeader = new JLabel("By Team 1 - I guess you could say we're #1");
        subHeader.setHorizontalTextPosition(JLabel.CENTER);
        subHeader.setHorizontalAlignment(JLabel.CENTER);
        subHeader.setFont(subHeader.getFont().deriveFont(16f));
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(header);
        headerPanel.add(subHeader);
        add(headerPanel, BorderLayout.NORTH);
        // level buttons
        add(createLevelButtons(), BorderLayout.CENTER);
        // footer
        var start = new JButton("Start!");
        add(start, BorderLayout.SOUTH);
        start.addActionListener(e -> {
            App.INSTANCE.startLevel(LevelSelector.get());
        });
    }

    private JPanel createLevelButtons() {
        // get copy of set of all levels and make it a list
        List<Level> levels = LevelLoader.loadAll().stream().toList();
        // create layout
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        // create panel
        JPanel panel = new JPanel(layout);
        // find the largest square that can fit all the buttons
        int gridSize = (int) Math.ceil(Math.sqrt(levels.size()));
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                // set the grid position of the button
                constraints.gridx = x;
                constraints.gridy = y;
                // create button for each level
                LevelSelector selector = new LevelSelector(levels.get(x + y * gridSize));
                panel.add(selector, constraints);
            }
        }
        return panel;
    }

    class LevelSelector extends JButton {
        private static final Color SELECTED = Color.GREEN;
        private static final Color UNSELECTED = Color.WHITE;

        private Level level;

        // there can only be one selected level
        private static LevelSelector selected;

        public LevelSelector(Level level) {
            super(level.getTitle());
            this.level = level;
            addActionListener(e -> {
                // deselect the previously selected level, if it exists
                if (selected != null)
                    selected.setBackground(UNSELECTED);
                // set the static selected level to this and set the
                // background to the highlight colour
                selected = this;
                setBackground(SELECTED);
            });
        }

        public Level getLevel() {
            return level;
        }

        public static Level get() {
            if (selected == null)
                throw new IllegalStateException("No level selected");
            return selected.getLevel();
        }
    }
}
