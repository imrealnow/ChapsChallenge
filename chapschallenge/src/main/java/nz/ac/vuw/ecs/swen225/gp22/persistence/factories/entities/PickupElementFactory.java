package nz.ac.vuw.ecs.swen225.gp22.persistence.factories.entities;

import java.util.function.Supplier;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Item;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Pickup;
import nz.ac.vuw.ecs.swen225.gp22.persistence.ElementFactory;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

public class PickupElementFactory implements ElementFactory<Entity> {

    @Override
    public Element createElement(Entity objectToConvert) {
        Pickup pickup = (Pickup) objectToConvert;
        Element root = DocumentHelper.createElement("Player");
        Vector pos = pickup.getPosition();
        Item item = pickup.getItem();
        root.addAttribute("x", Double.toString(pos.x()));
        root.addAttribute("y", Double.toString(pos.y()));
        root.addAttribute("type", pickup.getClass().getName());
        root.addAttribute("item", Integer.toString(item.ordinal()));
        return root;
    }

    @Override
    public Pickup createFromElement(Element element) {
        double x = Double.parseDouble(element.attributeValue("x"));
        double y = Double.parseDouble(element.attributeValue("y"));
        Item item = Item.values()[Integer.parseInt(element.attributeValue("item"))];
        return new Pickup(new Vector(x, y), item);
    }
}
