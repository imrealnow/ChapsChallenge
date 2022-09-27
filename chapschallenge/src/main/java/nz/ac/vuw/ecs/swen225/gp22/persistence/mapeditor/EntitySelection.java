package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;

public class EntitySelection extends JButton {
    private int index;
    private Entity entity;
    private final EntityPalette palette;

    EntitySelection(Entity entity, int index, EntityPalette palette) {
        super(new ImageIcon(entity.getSprite().sprite));
        setBorder(BorderFactory.createEmptyBorder());
        setToolTipText(entity.getClass().getSimpleName());
        setContentAreaFilled(false);
        this.index = index;
        this.entity = entity;
        this.palette = palette;
        setSize(32, 32);
        addActionListener(e -> {
            palette.setSelectedEntity(this);
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(32, 32);
    }

    public int index() {
        return index;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void highlight() {
        setBorder(BorderFactory.createLineBorder(new Color(77, 166, 255), 3));
    }

    public void unhighlight() {
        setBorder(BorderFactory.createEmptyBorder());
    }
}
