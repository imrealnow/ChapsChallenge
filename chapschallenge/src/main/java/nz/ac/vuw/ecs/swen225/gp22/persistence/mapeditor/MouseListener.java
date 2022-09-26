package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import java.awt.event.MouseEvent;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

public class MouseListener extends java.awt.event.MouseAdapter {
    private final TileGrid gridPanel;
    private final TilePalette palette;

    MouseListener(TileGrid gridPanel, TilePalette palette) {
        this.gridPanel = gridPanel;
        this.palette = palette;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        TileComponent tileComponent = gridPanel.getTileAtScreenPosition(new Vector(e.getX(), e.getY()));
        if (tileComponent != null) {
            Tile selectedTile = palette.getSelectedTile();
            if (selectedTile != null) {
                tileComponent.setTile(selectedTile);
                gridPanel.repaint();
            }
        }
    }
}
