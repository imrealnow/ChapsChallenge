package nz.ac.vuw.ecs.swen225.gp22.persistence;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import nz.ac.vuw.ecs.swen225.gp22.domain.Elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

/**
 * Factory for creating an XML element for a list of entities, and loading it
 * back
 */
public class EntityListElementFactory implements ElementFactory<List<Entity>> {
    // Entity factory
    enum EntityType {
        PLAYER("Player") {
            @Override
            public Entity createEntity(int x, int y) {
                return null;
            }
        },
        CHIP("Chip") {
            @Override
            public Entity createEntity(int x, int y) {
                return null;
            }
        };

        EntityType(String key) {
            this.key = key;
        }

        String key;

        public abstract Entity createEntity(int x, int y);

        public static EntityType fromKey(String key) {
            return EntityType.valueOf(key.toUpperCase());
        }
    }

    @Override
    public Element createElement(List<Entity> objectToConvert) {
        Element root = DocumentHelper.createElement("Entities");
        for (Entity entity : objectToConvert) {
            Element entityElement = DocumentHelper.createElement("Entity");
            Vector pos = entity.getPosition();
            entityElement.addAttribute("x", Double.toString(pos.x()));
            entityElement.addAttribute("y", Double.toString(pos.y()));
            entityElement.addAttribute("type", entity.getClass().getSimpleName());
            root.add(entityElement);
        }
        return root;
    }

    @Override
    public List<Entity> createFromElement(Element element) {
        List<Element> entities = element.elements("Entity");
        List<Entity> entityList = new ArrayList<>();
        for (Element entityElement : entities) {
            int x = Integer.parseInt(entityElement.attributeValue("x"));
            int y = Integer.parseInt(entityElement.attributeValue("y"));
            EntityType type = EntityType.fromKey(entityElement.attributeValue("type"));
            Entity entity = type.createEntity(x, y);
            entityList.add(entity);
        }
        return entityList;
    }

}
