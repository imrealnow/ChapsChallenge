package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;

public class EntityComponent extends JButton {
    private Entity entity;
    private final TileGrid gridPanel;

    public EntityComponent(Entity entity, TileGrid gridPanel) {
        this.entity = entity;
        this.gridPanel = gridPanel;
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public Dimension getPreferredSize() {
        Rectangle rect = getBounds();
        return new Dimension(rect.width, rect.height);
    }

    public void draw(Graphics g) {
        Rectangle rect = getBounds();
        g.drawImage(entity.getSprite().sprite, rect.x, rect.y, rect.width, rect.height, null);
    }

    // @Override
    // public void paintComponent(Graphics g) {
    // super.paintComponent(g);
    // Rectangle rect = getBounds();
    // g.drawImage(entity.getSprite().sprite, rect.x, rect.y, rect.width,
    // rect.height, null);
    // }
}
