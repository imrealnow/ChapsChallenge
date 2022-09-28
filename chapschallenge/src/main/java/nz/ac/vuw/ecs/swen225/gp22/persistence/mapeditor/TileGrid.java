package nz.ac.vuw.ecs.swen225.gp22.persistence.mapeditor;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.util.Vector;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.MouseEvent;

/**
 * JPanel that holds tiles in a grid
 */
public class TileGrid extends JPanel {
    // constants
    private static final int CELL_SIZE = 32;
    // grid
    private int cols;
    private int rows;
    // drawing
    private LevelToolbar.Tool currentTool;
    private double zoomLevel = 0.5;
    private Vector viewTarget;
    // objects
    private TileComponent[][] tiles;
    private Map<Vector, EntityComponent> entities = new HashMap<>();

    TileGrid(int cols, int rows) {
        super();
        setLayout(null);
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

    public void setTool(LevelToolbar.Tool tool) {
        this.currentTool = tool;
        if (tool == LevelToolbar.Tool.ENTITY) {
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            for (EntityComponent entityComponent : entities.values()) {
                entityComponent.setVisible(true);
            }
        } else {
            setCursor(Cursor.getDefaultCursor());
            for (EntityComponent entityComponent : entities.values()) {
                entityComponent.setVisible(false);
            }
        }
        repaint();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void initialise() {
        tiles = new TileComponent[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                TileComponent tile = TileComponent.empty(this, new Vector(x, y));
                tiles[y][x] = tile;
                add(tile);
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
        Tile[][] tiles = new Tile[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                tiles[y][x] = this.tiles[y][x].getTile();
            }
        }
        return tiles;
    }

    public void addEntity(Entity entity, Vector position) {
        EntityComponent entityComponent = new EntityComponent(entity, this);
        entities.put(position, entityComponent);
        add(entityComponent);
        setComponentZOrder(entityComponent, 0);
        Vector screenPosition = getScreenPos(position).add(getOffset());
        entityComponent.setBounds((int) screenPosition.x(), (int) screenPosition.y(), getCellSize(), getCellSize());
        repaint();
    }

    public void removeEntity(Vector position) {
        EntityComponent entityComponent = entities.remove(position);
        if (entityComponent != null) {
            remove(entityComponent);
            repaint();
        }
    }

    public Entity getEntity(Vector position) {
        EntityComponent entityComponent = entities.get(position);
        if (entityComponent != null) {
            return entityComponent.getEntity();
        }
        return null;
    }

    public void setEntity(Vector position, Entity entity) {
        EntityComponent entityComponent = entities.get(position);
        if (entityComponent != null) {
            entityComponent.setEntity(entity);
            repaint();
        }
    }

    public void setEntities(List<Entity> entities) {
        removeEntityComponents();
        for (Entity entity : entities) {
            addEntity(entity, entity.getPosition());
        }
    }

    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<>();
        for (EntityComponent entity : this.entities.values()) {
            entities.add(entity.getEntity());
        }
        return entities;
    }

    private void removeEntityComponents() {
        if (entities.size() > 0) {
            for (EntityComponent entity : entities.values()) {
                remove(entity);
            }
        }
        entities.clear();
    }

    private void removeTileComponents() {
        if (tiles != null) {
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    remove(this.tiles[y][x]);
                }
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
                tile.setBounds((int) screenPos.x(), (int) screenPos.y(), getCellSize(), getCellSize());
                tile.draw(g);
                if (currentTool == LevelToolbar.Tool.ENTITY) {
                    if (entities.containsKey(new Vector(x, y))) {
                        EntityComponent entity = entities.get(new Vector(x, y));
                        setComponentZOrder(entity, 0);
                        entity.setBounds((int) screenPos.x(), (int) screenPos.y(), getCellSize(), getCellSize());
                        entity.draw(g);
                    }
                }
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
