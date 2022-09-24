package com.github.imrealnow;

import javax.swing.JPanel;
import java.awt.*;

/**
 * JPanel that holds tiles in a grid
 */
public class TileGrid extends JPanel {
    private int spacing = 1;
    private int cols;
    private int rows;
    private TileComponent[][] tiles;

    TileGrid(int cols, int rows) {
        super(new GridLayout(cols, rows));
        this.cols = cols;
        this.rows = rows;
        this.tiles = new TileComponent[cols][rows];
        initialise();
    }

    TileGrid(Tile[][] tiles) {
        this(tiles.length, tiles[0].length);
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                this.tiles[x][y].setTile(tiles[x][y]);
            }
        }
    }

    public TileComponent getTile(int x, int y) {
        return tiles[y][x];
    }

    public void setTiles(Tile[][] tiles) {
        removeTileComponents();
        rows = tiles.length;
        cols = tiles[0].length;
        initialise();
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                this.tiles[x][y].setTile(tiles[x][y]);
            }
        }
        repaint();
    }

    public Tile[][] getTiles() {
        Tile[][] tiles = new Tile[cols][rows];
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                tiles[x][y] = this.tiles[x][y].getTile();
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

    public int getSpacing() {
        return spacing;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getBorderWidth() {
        return spacing;
    }

    public void initialise() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                TileComponent tile = TileComponent.empty(this);
                tiles[y][x] = tile;
                add(tile);
            }
        }
    }

    public Dimension getCellDimension() {
        // calculate square cell size that fits in the grid
        int totalWidth = getWidth() - (spacing * (cols + 1));
        int totalHeight = getHeight() - (spacing * (rows + 1));
        int cellSize = Math.min(totalWidth / cols, totalHeight / rows);
        return new Dimension(cellSize, cellSize);
    }

    private Dimension getGridDimension() {
        Dimension cellDimension = getCellDimension();
        return new Dimension(
                (cellDimension.width * cols) + (spacing * (cols + 1)),
                (cellDimension.height * rows) + (spacing * (rows + 1)));
    }

    private Dimension getOffsetDimension() {
        Dimension gridDimension = getGridDimension();
        return new Dimension(
                (getWidth() - gridDimension.width) / 2,
                (getHeight() - gridDimension.height) / 2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, getWidth(), getHeight());
        Dimension offsetDimension = getOffsetDimension();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (tiles[y][x] != null) {
                    tiles[y][x].draw(g, (int) offsetDimension.getWidth() + (x * (getCellDimension().width + spacing)),
                            (int) offsetDimension.getHeight() + (y * (getCellDimension().height + spacing)));
                }
            }
        }
    }

    public TileComponent getTileFromMousePos(int x, int y) {
        Dimension offsetDimension = getOffsetDimension();
        int cellWidth = getCellDimension().width + spacing;
        int cellHeight = getCellDimension().height + spacing;
        int tileX = (x / cellWidth) - (int) (offsetDimension.getWidth() / cellWidth);
        int tileY = (y / cellHeight) - (int) (offsetDimension.getHeight() / cellHeight);
        return tiles[tileY][tileX];
    }
}
