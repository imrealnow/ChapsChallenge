package nz.ac.vuw.ecs.swen225.gp22.persistence.factories.entities;

import java.util.function.Supplier;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities.Player;
import nz.ac.vuw.ecs.swen225.gp22.persistence.ElementFactory;
import nz.ac.vuw.ecs.swen225.gp22.persistence.EntityElementFactoryRegistry;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

public class PlayerElementFactory implements ElementFactory<Player> {
    {
        EntityElementFactoryRegistry.registerFactory(Player.class,
                (Supplier<ElementFactory<? extends Entity>>) () -> new PlayerElementFactory());
    }

    @Override
    public Element createElement(Player objectToConvert) {
        Element root = DocumentHelper.createElement("Player");
        Vector pos = objectToConvert.getPosition();
        root.addAttribute("x", Double.toString(pos.x()));
        root.addAttribute("y", Double.toString(pos.y()));
        root.addAttribute("type", objectToConvert.getClass().getSimpleName());
        return root;
    }

    @Override
    public Player createFromElement(Element element) {
        double x = Double.parseDouble(element.attributeValue("x"));
        double y = Double.parseDouble(element.attributeValue("y"));
        return new Player(new Vector(x, y));
    }
}
