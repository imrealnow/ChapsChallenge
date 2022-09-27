package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Grid;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Updateable;
import nz.ac.vuw.ecs.swen225.gp22.util.swing.NumberField;

public class LevelInfo extends JPanel implements Updateable {
    private LevelEditor levelEditor;
    private TileGrid tileGrid;
    private Level workingLevel;
    private NumberField levelTime;
    private NumberField gridWidth;
    private NumberField gridHeight;
    private JTextField levelName;

    public LevelInfo(LevelEditor levelEditor, TileGrid tileGrid) {
        super();
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.levelEditor = levelEditor;
        this.tileGrid = tileGrid;
        this.workingLevel = levelEditor.getLevel();
        this.levelName = new JTextField(workingLevel.getTitle(), 20);
        levelName.setMaximumSize(new Dimension(100, 20));
        levelName.addActionListener(e -> {
            levelEditor.setLevelName(levelName.getText());
        });
        this.levelTime = new NumberField(workingLevel.getTimeLimit(), 3);
        levelTime.setMaximumSize(new Dimension(100, 20));
        levelTime.addActionListener(e -> {
            levelEditor.setLevelTime(levelTime.getValue());
        });
        Tile[][] tiles = tileGrid.getTiles();
        this.gridWidth = new NumberField(tiles[0].length, 2);
        this.gridHeight = new NumberField(tiles.length, 2);
        add(createLevelSettingsPanel());
        add(createGridSettingsPanel());
    }

    private JPanel createLevelSettingsPanel() {
        JPanel levelSettingsPanel = new JPanel();
        levelSettingsPanel.setMaximumSize(new Dimension(150, 100));
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        levelSettingsPanel.setLayout(layout);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.2;
        constraints.weighty = 0;
        levelSettingsPanel.add(new JLabel("Level Name:"), constraints);
        constraints.gridx = 1;
        constraints.weightx = 1.5;
        levelSettingsPanel.add(levelName, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 0.2;
        levelSettingsPanel.add(new JLabel("Level Time:"), constraints);
        constraints.gridx = 1;
        constraints.weightx = 1.5;
        levelSettingsPanel.add(levelTime, constraints);
        return levelSettingsPanel;
    }

    private JPanel createGridSettingsPanel() {
        JPanel gridSettingsPanel = new JPanel(new FlowLayout());
        gridSettingsPanel.add(new JLabel("Grid Size:"));
        gridSettingsPanel.add(new JLabel("Width:"));
        gridSettingsPanel.add(gridWidth);
        gridSettingsPanel.add(new JLabel("Height:"));
        gridSettingsPanel.add(gridHeight);
        JButton resizeButton = new JButton("Resize");
        resizeButton.addActionListener(e -> {
            int width = gridWidth.getValue();
            int height = gridHeight.getValue();
            System.out.println("Resizing to " + width + "x" + height);
            levelEditor.resizeGrid(width, height);
        });
        gridSettingsPanel.add(resizeButton);
        return gridSettingsPanel;
    }

    @Override
    public void update() {
        workingLevel = levelEditor.getLevel();
        levelName.setText(workingLevel.getTitle());
        levelTime.setValue(workingLevel.getTimeLimit());
        Tile[][] tiles = tileGrid.getTiles();
        gridWidth.setValue(tiles[0].length);
        gridHeight.setValue(tiles.length);
        repaint();
    }
}
