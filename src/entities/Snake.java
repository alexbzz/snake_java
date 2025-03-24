package entities;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Snake extends Entity {
    private final LinkedList<Point> body;
    private int length;
    private String color;
    private int speed;
    private int life;
    private boolean status;
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
            body.add(new Point(body.getLast()));  // Ajouter de nouveaux segments à la fin
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
            case "UP" -> newHead.y -= 1;
            case "DOWN" -> newHead.y += 1;
            case "LEFT" -> newHead.x -= 1;
            case "RIGHT" -> newHead.x += 1;
        }

        body.addFirst(newHead);
        if (body.size() > length) {
            body.removeLast();
        }
        position = newHead;
    }

    public void changeDirection(String newDirection) {
        // Empêche le serpent de faire demi-tour
        if (direction.equals("UP") && newDirection.equals("DOWN")) return;
        if (direction.equals("DOWN") && newDirection.equals("UP")) return;
        if (direction.equals("LEFT") && newDirection.equals("RIGHT")) return;
        if (direction.equals("RIGHT") && newDirection.equals("LEFT")) return;

        this.direction = newDirection;
    }
    public String getDirection() {
        return direction;
    }
    public List<Point> getBody() {
        return body;
    }
}