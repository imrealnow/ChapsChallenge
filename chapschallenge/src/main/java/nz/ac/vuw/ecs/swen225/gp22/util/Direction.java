package nz.ac.vuw.ecs.swen225.gp22.util;

/**
 * Enum class to handle the four cardinal directions.
 * 
 * @author Liam Green - greenliam
 */
public enum Direction {
    Up(new Vector(0, -1)),
    Down(new Vector(0, 1)),
    Left(new Vector(-1, 0)),
    Right(new Vector(1, 0));

    Vector vectorValue;

    Direction(Vector vectorValue) {
        this.vectorValue = vectorValue;
    }

    /**
     * Returns the vector value of the direction.
     * 
     * @return the vector value of the direction.
     */
    public Vector vector() {
        return vectorValue;
    }

    /**
     * Returns the opposite direction.
     * 
     * @return the opposite direction.
     */
    Direction opposite() {
        switch (this) {
            case Up:
                return Down;
            case Down:
                return Up;
            case Left:
                return Right;
            case Right:
                return Left;
        }
        return null;
    }

    /**
     * Returns the direction that is closest to the angle of the vector.
     * 
     * @param vector The vector to find the closest direction to.
     * @return The closest direction to the vector.
     */
    public static Direction fromVector(Vector vector) {
        // use dot product to find the direction with the
        // closest angle to the vector
        double minAngle = Double.MAX_VALUE;
        Direction closestDirection = null;
        for (Direction direction : Direction.values()) {
            double angle = Math.acos(Vector.dotProduct(direction.vector(), vector));
            if (angle < minAngle) {
                minAngle = angle;
                closestDirection = direction;
            }
        }
        return closestDirection;
    }
}
