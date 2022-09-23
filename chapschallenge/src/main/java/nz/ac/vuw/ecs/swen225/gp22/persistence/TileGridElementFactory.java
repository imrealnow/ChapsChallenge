package nz.ac.vuw.ecs.swen225.gp22.persistence;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids.GridFence;

import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Element factory for a 2d array of tiles.
 * 
 * @author Liam Green - greenliam
 */
public class TileGridElementFactory implements ElementFactory<Tile[][]> {
    // Tile factory
    enum TileType {
        GRIDFENCE("GridFence") {
            @Override
            public Tile createTile(int x, int y) {
                return new GridFence();
            }
        };

        TileType(String key) {
            this.key = key;
        }

        String key;

        public abstract Tile createTile(int x, int y);

        public static TileType fromKey(String key) {
            return TileType.valueOf(key.toUpperCase());
        }
    }

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
                    tileElement.addAttribute("type", tile.getClass().getSimpleName());
                    root.add(tileElement);
                }
            }
        }
        return root;
    }

    @Override
    public Tile[][] createFromElement(Element element) {
        List<Element> tiles = element.elements("Tile");
        int width = Integer.parseInt(element.attributeValue("width"));
        int height = Integer.parseInt(element.attributeValue("height"));
        Tile[][] tileGrid = new Tile[width][height];
        for (Element tileElement : tiles) {
            int x = Integer.parseInt(tileElement.attributeValue("x"));
            int y = Integer.parseInt(tileElement.attributeValue("y"));
            TileType type = TileType.fromKey(tileElement.attributeValue("type"));
            tileGrid[y][x] = type.createTile(x, y);
        }
        return tileGrid;
    }
}
