package com.github.imrealnow;

public class Level {
    private final String name;
    private final String description;
    private final TileGrid map;

    public Level(int width, int height, String name, String description) {
        this.map = new TileGrid(width, height, 1);
        this.name = name;
        this.description = description;
    }

    Level(String mapString, String name, String description) {
        this.map = new TileGrid(mapString);
        this.name = name;
        this.description = description;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public TileGrid map() {
        return new TileGrid(map);
    }
}
