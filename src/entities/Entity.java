package entities;

import java.awt.*;

public abstract class Entity {
    protected Point position; // Current position of the entity
    protected int speed; // Speed of movement
    protected String direction; // Current movement direction (e.g., "UP", "DOWN", "LEFT", "RIGHT")

    // Constructor to initialize the entity with a starting position, speed, and direction
    public Entity(Point startPosition, int speed, String direction) {
        this.position = startPosition;
        this.speed = speed;
        this.direction = direction;
    }

    // Returns the current position of the entity
    public Point getPosition() {
        return position;
    }

    // Updates the movement direction of the entity
    public void setDirection(String direction) {
        this.direction = direction;
    }

    // Abstract method to be implemented by subclasses to define movement behavior
    public abstract void move();
}
