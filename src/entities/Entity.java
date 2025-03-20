package entities;

import java.awt.Point;

public abstract class Entity {
    protected Point position;
    protected int speed;
    protected String direction;

    public Entity(Point position, int speed, String direction) {
        this.position = position;
        this.speed = speed;
        this.direction = direction;
    }

    public Point getPosition() { return position; }
    public void setPosition(Point position) { this.position = position; }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public String getDirection() { return direction; }
    public void setDirection(String direction) { this.direction = direction; }

    public abstract void move();

}
