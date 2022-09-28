package nz.ac.vuw.ecs.swen225.gp22.persistence;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities.Player;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

/**
 * Factory for creating an XML element for a list of entities, and loading it
 * back
 * 
 * @author Liam Green - greenliam
 */
public class EntityListElementFactory implements ElementFactory<List<Entity>> {
    // Entity factory
    enum EntityType {
        PLAYER("Player") {
            @Override
            public Entity createEntity(int x, int y) {
                return new Player(new Vector((double) x, (double) y));
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
            var factory = EntityElementFactoryRegistry.getFactory(entity.getClass());
            Element entityElement = factory.createElement(entity);
            root.add(entityElement);
        }
        return root;
    }

    @Override
    public List<Entity> createFromElement(Element element) {
        List<Element> entities = element.elements();
        List<Entity> entityList = new ArrayList<>();
        for (Element entityElement : entities) {
            Class<? extends Entity> entityClass = EntityElementFactoryRegistry.getClassFromElement(entityElement);
            var factory = EntityElementFactoryRegistry.getFactory(entityClass);
            Entity entity = factory.createFromElement(entityElement);
            entityList.add(entity);
        }
        return entityList;
    }

}
