package objects;

import java.awt.Point;
import java.util.Random;

public class Food {
    private Point position; // Position of the food on the game grid
    private int quantity; // Amount of growth the food provides when eaten
    private String color; // Color of the food (e.g., "red")
    private String type; // Type of food (e.g., "normal", "special")
    private static final Random random = new Random(); // Random generator for food placement

    // Default constructor: Initializes food with default values
    public Food() {
        this.position = new Point(0, 0); // Default position at (0,0)
        this.quantity = 1; // Default quantity (snake grows by 1 when eating)
        this.color = "red"; // Default color
        this.type = "normal"; // Default type of food
    }

    // Parameterized constructor: Allows custom food properties
    public Food(Point position, int quantity, String color, String type) {
        this.position = position;
        this.quantity = quantity;
        this.color = color;
        this.type = type;
    }

    // Getters
    public Point getPosition() { return position; }
    public int getQuantity() { return quantity; }

    // Setters
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setColor(String color) { this.color = color; }
    public void setType(String type) { this.type = type; }

    // Generates a random position for the food within the game grid
    public void generateRandomPosition(int maxCols, int maxRows) {
        int x = random.nextInt(maxCols); // Random x-coordinate within bounds
        int y = random.nextInt(maxRows); // Random y-coordinate within bounds
        this.position = new Point(x, y); // Assign new random position
    }
}
