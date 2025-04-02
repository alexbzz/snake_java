package entities;

import java.awt.*;

public abstract class Entity {
    protected Point position;
    protected int speed;
    protected String direction;

    public Entity(Point startPosition, int speed, String direction) {
        this.position = startPosition;
        this.speed = speed;
        this.direction = direction;
    }

    public Point getPosition() {
        return position;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public abstract void move();
}
