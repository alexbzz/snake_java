package objects;


import java.awt.Point;
import java.util.Random;

public class Food {
    private Point position;
    private int quantity;
    private String color;
    private String type;
    private static final Random random = new Random();
    public Food() {
        this.position = new Point(0, 0);
        this.quantity = 1;
        this.color = "red";
        this.type = "normal";
    }

    public Food(Point position, int quantity, String color, String type) {
        this.position = position;
        this.quantity = quantity;
        this.color = color;
        this.type = type;
    }
    public Point getPosition() {
        return position;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void generateRandomPosition(int maxCols, int maxRows) {
        int x = random.nextInt(maxCols);
        int y = random.nextInt(maxRows);
        this.position = new Point(x, y);
    }
}
