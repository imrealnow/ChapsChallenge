package com.github.imrealnow;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Factory for creating an XML element for a list of entities, and loading it
 * back
 * 
 * @author Liam Green - greenliam
 */
public class EntityListElementFactory implements ElementFactory<List<Entity>> {
    @Override
    public Element createElement(List<Entity> objectToConvert) {
        Element root = DocumentHelper.createElement("Entities");
        for (Entity entity : objectToConvert) {
            Element entityElement = DocumentHelper.createElement("Entity");
            entityElement.addAttribute("x", Integer.toString(entity.getX()));
            entityElement.addAttribute("y", Integer.toString(entity.getY()));
            entityElement.addAttribute("type", entity.getName());
            root.add(entityElement);
        }
        return root;
    }

    @Override
    public List<Entity> createFromElement(Element element) {
        List<Element> entities = element.elements();
        List<Entity> entityList = new ArrayList<>();
        for (Element entityElement : entities) {
            int x = Integer.parseInt(entityElement.attributeValue("x"));
            int y = Integer.parseInt(entityElement.attributeValue("y"));
            String type = entityElement.attributeValue("type");
            Entity entity = Entity.getFromName(type);
            entity.setPosition(x, y);
            entityList.add(entity);
        }
        return entityList;
    }
}
