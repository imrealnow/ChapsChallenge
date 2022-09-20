package com.github.imrealnow;

import javax.swing.JPanel;
import java.awt.*;

/**
 * JPanel that holds tiles in a grid
 */
public class TileGrid extends JPanel {
    private int spacing;
    private int cols;
    private int rows;
    private TileComponent[][] tiles;

    TileGrid(int cols, int rows, int borderWidth) {
        super(new GridLayout(cols, rows));
        this.cols = cols;
        this.rows = rows;
        this.spacing = borderWidth;
        this.tiles = new TileComponent[cols][rows];
        initialise();
    }

    TileGrid(String gridString) {
        super();
        String[] lines = gridString.split("\n");
        String[] dimensions = lines[0].split(",");
        this.cols = Integer.parseInt(dimensions[0]);
        this.rows = Integer.parseInt(dimensions[1]);
        setLayout(new GridLayout(cols, rows));
        this.tiles = new TileComponent[rows][cols];
        this.spacing = 1;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                int c = Integer.parseInt(lines[y + 1].charAt(x) + "");
                Tile tile = Tile.getFromId(c);
                TileComponent tileComponent = new TileComponent(tile, this);
                tiles[y][x] = tileComponent;
                add(tileComponent);
            }
        }
    }

    TileGrid(TileGrid grid) {
        this(grid.getRows(), grid.getCols(), grid.getSpacing());
        for (int y = 0; y < grid.getRows(); y++) {
            for (int x = 0; x < grid.getCols(); x++) {
                TileComponent tileComponent = grid.getTile(x, y);
                tiles[y][x] = tileComponent;
                add(tileComponent);
            }
        }
    }

    public TileComponent getTile(int x, int y) {
        return tiles[y][x];
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        g.drawRect(0, 0, getWidth(), getHeight());
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (tiles[y][x] != null) {
                    tiles[y][x].draw(g, x * (getCellDimension().width + spacing),
                            y * (getCellDimension().height + spacing));
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(cols + "," + rows + "\n");
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                sb.append(tiles[y][x].getTile().getId());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public TileComponent getTileFromMousePos(int x, int y) {
        int cellWidth = getCellDimension().width + spacing;
        int cellHeight = getCellDimension().height + spacing;
        int tileX = x / cellWidth;
        int tileY = y / cellHeight;
        return tiles[tileY][tileX];
    }
}
