package entities;

import java.awt.Point;
import java.util.LinkedList;

public class Snake extends Entity {
    private LinkedList<Point> body;
    private int length;
    private String color;
    private int speed;
    private int life;
    private boolean status; // Actif ou non
    private String skin;

    public Snake(Point startPosition, int speed, String color, String skin) {
        super(startPosition, speed, "RIGHT");
        this.body = new LinkedList<>();
        this.body.add(startPosition);
        this.length = 1;
        this.color = color;
        this.speed = speed;
        this.life = 3;
        this.status = true;
        this.skin = skin;
    }

    public int getLength() { return length; }
    public String getColor() { return color; }
    public int getSpeed() { return speed; }
    public int getLife() { return life; }
    public boolean getStatus() { return status; }
    public String getSkin() { return skin; }

    public void setLength(int length) { this.length = length; }
    public void setColor(String color) { this.color = color; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setLife(int life) { this.life = life; }
    public void setStatus(boolean status) { this.status = status; }
    public void setSkin(String skin) { this.skin = skin; }

    public void grow(int values) {
        this.length += values;
        for (int i = 0; i < values; i++) {
            body.add(new Point(body.getLast()));
        }
    }

    public void reduce(int values) {
        if (this.length > values) {
            this.length -= values;
            for (int i = 0; i < values; i++) {
                body.removeLast();
            }
        }
    }

    public void accelerate(int values) {
        this.speed += values;
    }

    public void slow(int values) {
        if (this.speed > values) {
            this.speed -= values;
        }
    }

    public void loseLife(int values) {
        this.life -= values;
        if (this.life <= 0) {
            this.status = false;
        }
    }

    public boolean isDead() {
        return this.life <= 0;
    }

    @Override
    public void move() {
        Point newHead = new Point(position);
        switch (direction) {
            case "UP" -> newHead.y -= speed;
            case "DOWN" -> newHead.y += speed;
            case "LEFT" -> newHead.x -= speed;
            case "RIGHT" -> newHead.x += speed;
        }

        body.addFirst(newHead);
        if (body.size() > length) {
            body.removeLast();
        }
        position = newHead;
    }
    public void changeDirection(String up) {
        switch (direction) {
            case "UP" -> direction = "UP";
            case "DOWN" -> direction = "DOWN";
            case "LEFT" -> direction = "LEFT";
            case "RIGHT" -> direction = "RIGHT";
        }
    }
}
