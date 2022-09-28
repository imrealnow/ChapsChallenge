package nz.ac.vuw.ecs.swen225.gp22.persistence;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.DocumentException;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;

/**
 * Class used for loading a level from an XML file.
 * 
 * @author Liam Green - greenliam
 */
public class LevelFactory implements XMLFactory<Level> {
    EntityListElementFactory entityListElementFactory = new EntityListElementFactory();
    TileGridElementFactory tileGridElementFactory = new TileGridElementFactory();

    @Override
    public File createXML(String filePath, Level objectToSave) throws IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Level");
        root.addAttribute("Title", objectToSave.getTitle());
        root.addAttribute("TimeLimit",
                Integer.toString(objectToSave.getTimeLimit()));
        Element tileGrid = tileGridElementFactory.createElement(objectToSave.getTiles());
        Element entityList = entityListElementFactory.createElement(objectToSave.getEntities());
        root.add(tileGrid);
        root.add(entityList);
        return XMLSerializer.writeDocumentToXML(document, filePath);
    }

    @Override
    public Level createFromXML(InputStream xmlStream) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(xmlStream);
        Element root = document.getRootElement();
        String name = root.attributeValue("Title");
        int timeLimit = Integer.parseInt(root.attributeValue("TimeLimit"));
        Element entityListElement = root.element("Entities");
        Element tileGridElement = root.element("TileGrid");
        return new Level(tileGridElementFactory.createFromElement(tileGridElement),
                entityListElementFactory.createFromElement(entityListElement), name, timeLimit);
    }
}
