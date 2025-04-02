package entities;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Snake extends Entity {
    private final LinkedList<Point> body; // List representing the snake's body segments
    private int length; // Current length of the snake
    private String colorHex; // Color of the snake in hexadecimal format
    private int speed; // Speed of movement
    private int life; // Number of lives the snake has
    private boolean status; // True if the snake is active, false if dead
    private String skin; // Skin type for the snake

    // Constructor to initialize the snake with its starting position, speed, color, and skin
    public Snake(Point startPosition, int speed, String colorHex, String skin) {
        super(startPosition, speed, "RIGHT"); // Default direction is "RIGHT"
        this.body = new LinkedList<>();
        this.body.add(startPosition);
        this.length = 1;
        this.colorHex = colorHex;
        this.speed = speed;
        this.life = 3; // Default number of lives
        this.status = true; // Snake starts as active
        this.skin = skin;
    }

    // Getters
    public String getColor() { return colorHex; }
    public int getLength() { return length; }
    public int getSpeed() { return speed; }
    public int getLife() { return life; }
    public boolean getStatus() { return status; }
    public String getSkin() { return skin; }
    public List<Point> getBody() { return body; }
    public String getDirection() { return direction; }

    // Setters
    public void setColor(String colorHex) { this.colorHex = colorHex; }
    public void setLength(int length) { this.length = length; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setLife(int life) { this.life = life; }
    public void setStatus(boolean status) { this.status = status; }
    public void setSkin(String skin) { this.skin = skin; }

    // Increase the length of the snake when eating food
    public void grow(int values) {
        this.length += values;
        for (int i = 0; i < values; i++) {
            body.add(new Point(body.getLast())); // Adds new segments at the tail
        }
    }

    // Change the movement direction of the snake
    public void changeDirection(String newDirection) {
        if (!isOppositeDirection(newDirection)) { // Prevents 180-degree turns
            this.direction = newDirection;
        }
    }

    // Check if the new direction is the opposite of the current one
    private boolean isOppositeDirection(String newDirection) {
        return (direction.equals("UP") && newDirection.equals("DOWN")) ||
                (direction.equals("DOWN") && newDirection.equals("UP")) ||
                (direction.equals("LEFT") && newDirection.equals("RIGHT")) ||
                (direction.equals("RIGHT") && newDirection.equals("LEFT"));
    }

    // Moves the snake in its current direction
    @Override
    public void move() {
        Point newHead = calculateNewHead(); // Determine the new position of the head
        body.addFirst(newHead); // Add the new head at the front of the body
        if (body.size() > length) {
            body.removeLast(); // Remove the last segment to maintain length
        }
        position = newHead; // Update the snake's position
    }

    // Calculate the new position of the snake's head based on the direction
    private Point calculateNewHead() {
        Point head = new Point(position);
        switch (direction) {
            case "UP" -> head.y -= 1;
            case "DOWN" -> head.y += 1;
            case "LEFT" -> head.x -= 1;
            case "RIGHT" -> head.x += 1;
        }
        return head;
    }
}
