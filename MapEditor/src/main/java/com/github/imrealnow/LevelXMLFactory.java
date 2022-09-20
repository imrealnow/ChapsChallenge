package com.github.imrealnow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class LevelXMLFactory implements XMLFactory<Level> {
    Level level;

    LevelXMLFactory(Level level) {
        this.level = level;
    }

    public File toXML(String path, String fileName) throws IOException {
        Document document = DocumentHelper.createDocument();
        // root level element
        Element root = document.addElement("level");
        root.addAttribute("name", level.name());
        root.addAttribute("description", level.description());
        // tile map element
        Element map = root.addElement("map");
        String mapString = level.map().toString();
        String[] mapLines = mapString.split("\n");
        for (String line : mapLines) {
            map.addElement("row").addText(line);
        }
        // write to file
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(format);
        File file = new File(path + fileName + ".xml");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            writer.setOutputStream(fos);
            writer.write(document);
        }
        return file;
    }

    public Level fromXML(File xmlFile) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(xmlFile);
        Element root = document.getRootElement();
        // read level attributes
        String name = root.attributeValue("name");
        String description = root.attributeValue("description");
        // read tile map
        Element map = root.element("map");
        StringBuilder mapBuilder = new StringBuilder();
        for (Element row : map.elements("row")) {
            mapBuilder.append(row.getText());
            mapBuilder.append("\n");
        }
        // create level
        String mapString = mapBuilder.toString();
        Level level = new Level(mapString, name, description);
        return level;
    }

}
