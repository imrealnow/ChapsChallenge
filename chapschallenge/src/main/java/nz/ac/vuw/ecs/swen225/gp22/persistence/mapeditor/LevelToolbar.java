package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class LevelToolbar extends JTabbedPane {
    private final TileGrid tileGrid;
    private final LevelEditor levelEditor;

    LevelToolbar(TileGrid tileGrid, LevelEditor levelEditor) {
        super();
        this.tileGrid = tileGrid;
        this.levelEditor = levelEditor;
        setPreferredSize(new Dimension(200, 600));
        setMinimumSize(getPreferredSize());
        addTab("Tiles", createTilePaletteTab());
        addTab("Entities", createEntityPaletteTab());
        addTab("Info", createInfoTab());
    }

    private TilePalette createTilePaletteTab() {
        TilePalette tilePalette = new TilePalette();
        tilePalette.setMinimumSize(new Dimension(150, 600));
        MouseListener mouseListener = new MouseListener(tileGrid, tilePalette);
        tileGrid.addMouseListener(mouseListener);
        return tilePalette;
    }

    private JPanel createEntityPaletteTab() {
        JPanel entityPalette = new JPanel();
        entityPalette.setMinimumSize(new Dimension(150, 600));
        return entityPalette;
    }

    private JPanel createInfoTab() {
        JPanel info = new JPanel();
        info.setMinimumSize(new Dimension(150, 600));
        return info;
    }
}
