package com.github.imrealnow;

import java.awt.Color;

public enum Entity {
    Player("Player", Color.blue),
    Friend("Pickup", Color.yellow);

    Entity(String simpleName, Color color) {
        this.entitySimpleName = simpleName;
        this.color = color;
    }

    private final String entitySimpleName;
    private final Color color;

    private int x;
    private int y;

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Entity getFromName(String simpleName) {
        for (Entity entity : values()) {
            if (entity.entitySimpleName.equals(simpleName)) {
                return entity;
            }
        }
        return null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return entitySimpleName;
    }

    public Color getColor() {
        return color;
    }
}
