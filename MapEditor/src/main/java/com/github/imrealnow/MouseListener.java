package com.github.imrealnow;

import java.awt.event.MouseEvent;

public class MouseListener extends java.awt.event.MouseAdapter {
    private final TileGrid gridPanel;
    private final TilePalette palette;

    MouseListener(TileGrid gridPanel, TilePalette palette) {
        this.gridPanel = gridPanel;
        this.palette = palette;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        TileComponent tileComponent = gridPanel.getTileFromMousePos(e.getX(), e.getY());
        if (tileComponent != null) {
            Tile selectedTile = palette.getSelectedTile();
            if (selectedTile != null) {
                tileComponent.setTile(selectedTile);
                gridPanel.repaint();
            }
        }
    }
}
