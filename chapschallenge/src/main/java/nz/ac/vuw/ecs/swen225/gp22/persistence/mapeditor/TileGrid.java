package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

import java.awt.*;

/**
 * JPanel that holds tiles in a grid
 */
public class TileGrid extends JPanel {
    private int cols;
    private int rows;
    private double zoomLevel = 1.0;
    private int cellSize = 32;
    private Vector viewTarget;
    private TileComponent[][] tiles;

    TileGrid(int cols, int rows) {
        super(new GridLayout(cols, rows));
        this.cols = cols;
        this.rows = rows;
        this.tiles = new TileComponent[cols][rows];
        this.viewTarget = new Vector(getWidth() / 2, getHeight() / 2);
        initialise();
    }

    TileGrid(Tile[][] tiles) {
        this(tiles.length, tiles[0].length);
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                this.tiles[y][x].setTile(tiles[y][x]);
            }
        }
    }

    public void setTiles(Tile[][] tiles) {
        removeTileComponents();
        rows = tiles.length;
        cols = tiles[0].length;
        initialise();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                this.tiles[y][x].setTile(tiles[y][x]);
            }
        }
        repaint();
    }

    public Tile[][] getTiles() {
        Tile[][] tiles = new Tile[cols][rows];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                tiles[y][x] = this.tiles[y][x].getTile();
            }
        }
        return tiles;
    }

    private void removeTileComponents() {
        if (tiles != null) {
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    remove(this.tiles[x][y]);
                }
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void initialise() {
        tiles = new TileComponent[cols][rows];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                TileComponent tile = TileComponent.empty(this);
                tiles[y][x] = tile;
                add(tile);
            }
        }
    }

    public int getCellSize() {
        return (int) zoomLevel * (getWidth() / (cols));
    }

    /**
     * get screen position from grid coordinates centered on viewTarget.
     * 
     * @param pos grid coordinates
     * @return screen coordinates
     */
    private Vector getScreenPos(Vector pos) {
        // 0,0 is top left of grid:
        // grid 0,0 is at top left of screen when viewTarget is screenWidth/2,
        // screenHeight/2
        // and zoom level is 1.0
        Vector screenPos = pos.resize(getCellSize());
        screenPos = screenPos.add(viewTarget);
        return screenPos;
    }

    /**
     * get grid coordinates from screen coordinates centered on viewTarget.
     * 
     * @param pos screen coordinates
     * @return grid coordinates
     */
    private Vector getGridPos(Vector pos) {
        Vector gridPos = pos.subtract(viewTarget);
        gridPos = gridPos.resize(1.0 / getCellSize());
        return gridPos;
    }

    public TileComponent getTileAtScreenPosition(Vector screenPosition) {
        System.out.println("screenPosition: " + screenPosition.x() + ", " + screenPosition.y());
        Vector gridPos = getGridPos(screenPosition);
        System.out.println("gridPos: " + gridPos.x() + ", " + gridPos.y());
        int x = (int) gridPos.x();
        int y = (int) gridPos.y();
        if (x < 0 || x >= cols || y < 0 || y >= rows) {
            return null;
        }
        return tiles[y][x];
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                Vector screenPos = getScreenPos(new Vector(x, y));
                TileComponent tile = tiles[y][x];
                tile.draw(g, (int) screenPos.x(), (int) screenPos.y(), getCellSize(), getCellSize());
            }
        }
    }
}
