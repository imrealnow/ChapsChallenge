package nz.ac.vuw.ecs.swen225.gp22.persistence;

import java.io.File;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.DocumentException;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;

public class LevelFactory implements XMLFactory<Level> {
    EntityListElementFactory entityListElementFactory = new EntityListElementFactory();
    TileGridElementFactory tileGridElementFactory = new TileGridElementFactory();

    @Override
    public File createXML(String filePath, Level objectToSave) throws IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Level");
        // root.addAttribute("name", objectToSave.getName());
        // root.addAttribute("id", Integer.toString(objectToSave.getId()));
        // root.addAttribute("TimeLimit",
        // Integer.toString(objectToSave.getTimeLimit()));
        root.add(entityListElementFactory.createElement(objectToSave.getEntities()));
        root.add(tileGridElementFactory.createElement(objectToSave.getTiles()));
        return XMLSerializer.writeDocumentToXML(document, filePath);
    }

    @Override
    public Level createFromXML(File xmlFile) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(xmlFile);
        Element root = document.getRootElement();
        // String name = root.attributeValue("name");
        // int id = Integer.parseInt(root.attributeValue("id"));
        // int timeLimit = Integer.parseInt(root.attributeValue("TimeLimit"));
        Element entityListElement = root.element("EntityList");
        Element tileGridElement = root.element("TileGrid");
        // return new
        // Level(entityListElementFactory.createEntityList(entityListElement),
        // tileGridElementFactory.createTileGrid(tileGridElement));
        return null;
    }
}
