package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.AWTEvent;
import java.awt.event.MouseEvent;

public class LevelToolbar extends JTabbedPane {
    public enum Tool {
        TILE,
        ENTITY,
        INFO;
    }

    private Tool currentTool = Tool.TILE;
    private final TileGrid tileGrid;
    private final LevelEditor levelEditor;
    private final TilePalette tilePalette;
    private final EntityPalette entityPalette;
    private final LevelInfo levelInfo;

    LevelToolbar(TileGrid tileGrid, LevelEditor levelEditor) {
        super();
        this.tileGrid = tileGrid;
        this.levelEditor = levelEditor;
        setPreferredSize(new Dimension(200, 600));
        setMinimumSize(getPreferredSize());
        tilePalette = createTilePaletteTab();
        entityPalette = createEntityPaletteTab();
        levelInfo = createInfoTab();
        addTab("Tiles", tilePalette);
        addTab("Entities", entityPalette);
        addTab("Info", levelInfo);
        createEventListener();
        getModel().addChangeListener(e -> {
            if (getSelectedIndex() == 0) {
                currentTool = Tool.TILE;
            } else if (getSelectedIndex() == 1) {
                currentTool = Tool.ENTITY;
            } else if (getSelectedIndex() == 2) {
                currentTool = Tool.INFO;
            }
            levelEditor.onToolChanged(currentTool);
        });
    }

    private void createEventListener() {
        LevelToolbar self = this;
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            public void eventDispatched(AWTEvent e) {
                if (e.getSource() instanceof TileComponent) {
                    TileComponent tileComponent = (TileComponent) e.getSource();
                    switch (e.getID()) {
                        case MouseEvent.MOUSE_CLICKED:
                            if (currentTool == Tool.TILE) {
                                self.tilePalette.onGridClick(tileComponent, e);
                            } else if (currentTool == Tool.ENTITY) {
                                self.entityPalette.onGridClick(tileComponent, e);
                            }
                    }
                }
            }
        }, AWTEvent.MOUSE_EVENT_MASK);
    }

    private TilePalette createTilePaletteTab() {
        TilePalette tilePalette = new TilePalette(tileGrid);
        tilePalette.setMinimumSize(new Dimension(150, 600));
        return tilePalette;
    }

    private EntityPalette createEntityPaletteTab() {
        EntityPalette entityPalette = new EntityPalette(tileGrid);
        entityPalette.setMinimumSize(new Dimension(150, 600));
        return entityPalette;
    }

    private LevelInfo createInfoTab() {
        LevelInfo info = new LevelInfo(levelEditor, tileGrid);
        info.setMinimumSize(new Dimension(150, 600));
        return info;
    }

    public void updateToolbar() {
        levelInfo.update();
    }
}
