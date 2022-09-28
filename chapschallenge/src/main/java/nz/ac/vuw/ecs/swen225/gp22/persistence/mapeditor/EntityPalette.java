package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.lang.reflect.Constructor;
import java.awt.GridBagConstraints;
import java.awt.AWTEvent;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Item;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Pickup;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities.*;
import nz.ac.vuw.ecs.swen225.gp22.persistence.factories.entities.EntityFactory;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

public class EntityPalette extends JPanel {

    private static Entity[] ENTITY_TYPES = {
            new Player(new Vector(0, 0)),
            new Pickup(new Vector(0, 0), Item.ItemFriend),
            new Pickup(new Vector(0, 0), Item.ItemKeyBlue),
            new Pickup(new Vector(0, 0), Item.ItemKeyRed),
            new Pickup(new Vector(0, 0), Item.ItemKeyYellow),
            new Pickup(new Vector(0, 0), Item.ItemKeySilver),
    };
    private final EntitySelection[] entities;
    private final TileGrid tileGrid;
    private Entity selectedEntity;
    private EntitySelection currentSelection;

    public EntityPalette(TileGrid tileGrid) {
        super();
        this.tileGrid = tileGrid;
        this.entities = new EntitySelection[ENTITY_TYPES.length];
        add(createEntityPanel());
    }

    private JPanel createEntityPanel() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.ipadx = 5;
        constraints.ipady = 5;
        JPanel entityPanel = new JPanel(layout);
        entityPanel.setBackground(Color.lightGray);
        for (int i = 0; i < entities.length; i++) {
            entities[i] = new EntitySelection(ENTITY_TYPES[i], i, this);
            constraints.weightx = 0.5;
            constraints.weighty = 0;
            constraints.gridx = i % 5;
            constraints.gridy = i / 5;
            entityPanel.add(entities[i], constraints);
        }
        return entityPanel;
    }

    public void setSelectedEntity(EntitySelection entitySelection) {
        if (currentSelection != null) {
            currentSelection.setSelected(false);
        }
        currentSelection = entitySelection;
        currentSelection.setSelected(true);
        selectedEntity = entitySelection.getEntity();
    }

    public Entity getSelectedEntity() {
        return selectedEntity;
    }

    public void onGridClick(TileComponent tile, AWTEvent e) {
        if (selectedEntity != null) {
            try {
                Vector position = tile.getPosition();
                Entity entity = EntityFactory.create(selectedEntity, position);
                tileGrid.addEntity(entity, position);
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
}
