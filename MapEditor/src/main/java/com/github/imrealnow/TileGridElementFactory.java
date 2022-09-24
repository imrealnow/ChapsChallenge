package com.github.imrealnow;

import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Element factory for a 2d array of tiles.
 * 
 * @author Liam Green - greenliam
 */
public class TileGridElementFactory implements ElementFactory<Tile[][]> {
    @Override
    public Element createElement(Tile[][] objectToConvert) {
        Element root = DocumentHelper.createElement("TileGrid");
        root.addAttribute("width", Integer.toString(objectToConvert.length));
        root.addAttribute("height", Integer.toString(objectToConvert[0].length));
        for (int y = 0; y < objectToConvert.length; y++) {
            for (int x = 0; x < objectToConvert[y].length; x++) {
                Tile tile = objectToConvert[y][x];
                if (tile != null) {
                    Element tileElement = DocumentHelper.createElement("Tile");
                    tileElement.addAttribute("x", Integer.toString(x));
                    tileElement.addAttribute("y", Integer.toString(y));
                    tileElement.addAttribute("type", tile.getName());
                    root.add(tileElement);
                }
            }
        }
        return root;
    }

    @Override
    public Tile[][] createFromElement(Element element) {
        int width = Integer.parseInt(element.attributeValue("width"));
        int height = Integer.parseInt(element.attributeValue("height"));
        Tile[][] tileGrid = new Tile[width][height];
        List<Element> tiles = element.elements();
        for (Element tileElement : tiles) {
            int x = Integer.parseInt(tileElement.attributeValue("x"));
            int y = Integer.parseInt(tileElement.attributeValue("y"));
            String type = tileElement.attributeValue("type");
            Tile tile = Tile.getFromName(type);
            tileGrid[y][x] = tile;
        }
        return tileGrid;
    }
}
