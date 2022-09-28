package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseEvent;

/**
 * JPanel that holds tiles in a grid
 */
public class TileGrid extends JPanel {
    private int cols;
    private int rows;
    private double zoomLevel = 0.5;
    private int cellSize = 32;
    private Vector viewTarget;
    private TileComponent[][] tiles;

    TileGrid(int cols, int rows) {
        super(new GridLayout(cols, rows));
        this.cols = cols;
        this.rows = rows;
        this.tiles = new TileComponent[cols][rows];
        this.viewTarget = new Vector(cols / 2, rows / 2);
        NavigationListener navigationListener = new NavigationListener();
        addMouseWheelListener(navigationListener);
        addMouseMotionListener(navigationListener);
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
        return (int) Math.round((getWidth() / (cols)) * zoomLevel);
    }

    public Vector getGridDimension() {
        return new Vector(cols * getCellSize(), rows * getCellSize());
    }

    /**
     * get screen position from grid coordinates centered on viewTarget.
     * 
     * @param pos grid coordinates
     * @return screen coordinates
     */
    private Vector getScreenPos(Vector pos) {
        Vector screenPos = pos.resize(getCellSize());
        screenPos = screenPos.add(new Vector(getWidth() / 2, getHeight() / 2));
        screenPos = screenPos.subtract(getGridDimension().resize(0.5));
        return screenPos;
    }

    private Vector getOffset() {
        Vector viewTargetScreenPos = getScreenPos(viewTarget);
        return new Vector(getWidth() / 2, getHeight() / 2).subtract(viewTargetScreenPos);
    }

    /**
     * get grid coordinates from screen coordinates centered on viewTarget.
     * 
     * @param pos screen coordinates
     * @return grid coordinates
     */
    private Vector getGridPos(Vector pos) {
        Vector gridPos = pos.subtract(getGridDimension().resize(0.5));
        gridPos = gridPos.subtract(getOffset());
        gridPos = gridPos.resize(1.0 / getCellSize());
        return gridPos;
    }

    public TileComponent getTileAtScreenPosition(Vector screenPosition) {
        Vector gridPos = getGridPos(screenPosition);
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
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                Vector screenPos = getScreenPos(new Vector(x, y)).add(getOffset());
                TileComponent tile = tiles[y][x];
                tile.draw(g, (int) screenPos.x(), (int) screenPos.y(), getCellSize(), getCellSize());
                tile.setBounds((int) screenPos.x(), (int) screenPos.y(), getCellSize(), getCellSize());
            }
        }
    }

    class NavigationListener extends MouseAdapter {
        private MouseEvent lastEvent;

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (e.getPoint().x < getWidth() && e.getPoint().y < getHeight()) {
                if (e.getWheelRotation() < 0) {
                    zoomLevel += 0.1;
                } else {
                    zoomLevel -= 0.1;
                }
                zoomLevel = Math.max(0.1, zoomLevel);
                zoomLevel = Math.min(1.5, zoomLevel);
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (!SwingUtilities.isMiddleMouseButton(e))
                return;

            if (e.getPoint().x < getWidth() && e.getPoint().y < getHeight()) {
                if (lastEvent != null) {
                    Vector delta = new Vector(e.getX() - lastEvent.getX(), e.getY() - lastEvent.getY());
                    delta = delta.resize(1.0 / getCellSize());
                    viewTarget = viewTarget.subtract(delta);
                    repaint();
                }
            }
            lastEvent = e;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            lastEvent = null;
        }
    }
}
