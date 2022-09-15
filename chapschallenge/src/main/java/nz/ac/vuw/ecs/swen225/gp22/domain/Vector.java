package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * A Vector is a pair of Coordinates (x and y) used to denote the position of an
 * Entity.
 * Contains a variety of helper functions to streamline the process of working
 * with two different positions.
 */
public record Vector(double x, double y) {

    /**
     * Adds the specified x and y values to this vector.
     * 
     * @param x The amount to shift this vector by along the x axis.
     * @param y The amount to shift this vector by along the y axis.
     * @return A vector of this vectors old size, plus the specified x and y.
     */
    public Vector add(double x, double y) {
        return new Vector(x() + x, y() + y);
    }

    /**
     * Adds two vectors together
     * 
     * @param o The vector to combine with this vector.
     * @return A vector of this vectors old size, plus the x and y of the other
     *         vector.
     */
    public Vector add(Vector o) {
        return add(o.x, o.y);
    }

    /**
     * Scales this vector by scaleX along the x axis, and scaleY along the y axis.
     * 
     * @param scaleX
     * @param scaleY
     * @return A vector with its x and y values scaled by the specified amounts.
     */
    public Vector scale(double scaleX, double scaleY) {
        return new Vector(x * scaleX, y * scaleY);
    }

    /**
     * Similarly to scale, this function resizes a vector by the same amount in the
     * x and y direction.
     * 
     * @param scale
     * @return The resized vector.
     */
    public Vector resize(double scale) {
        return new Vector(x() * scale, y() * scale);
    }

    /**
     * Returns the magnitude/length of a given vector.
     * 
     * @return The length of the vector.
     */
    public double size() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Returns the distance between two vectors.
     * 
     * @param o The other vector to compare the distance with.
     * @return The resultant distance betweent the two vectors.
     */
    public double distance(Vector o) {
        return Math.sqrt(Math.pow(x - o.x(), 2) + Math.pow(y - o.y(), 2));
    }
}