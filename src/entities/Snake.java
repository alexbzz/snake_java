package entities;

import java.awt.Point;
import java.util.LinkedList;

public class Snake extends Entity {
    private LinkedList<Point> body;
    public Snake(Point startPosition, int speed) {
        super(startPosition, speed, "RIGHT");
        body = new LinkedList<>();
        body.add(startPosition);
    }
    public LinkedList<Point> getBody() { return body; }
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
        body.removeLast();
        position = newHead;
    }
    }
