package view;

import entities.Snake;
import objects.Food;
import javax.swing.*;
import java.awt.*;
import java.util.List;


public class GameLayer extends JPanel {
    private final Snake snake;
    private final List<Food> foods;
    private final int cellSize;
    private Color backgroundColor;

    public GameLayer(Snake snake, List<Food> foods, int cellSize) {
        this.snake = snake;
        this.foods = foods;
        this.cellSize = cellSize;
        this.backgroundColor = new Color(240, 240, 240);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawSnake(g);
        drawFood(g);
    }

    // Draws the game background
    private void drawBackground(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    // Draws the snake with head and body segments
    private void drawSnake(Graphics g) {
        Color snakeColor = parseColor(snake.getColor());

        // Draw head (different from body)
        Point head = snake.getBody().getFirst();
        g.setColor(snakeColor.darker());
        g.fillRoundRect(head.x * cellSize, head.y * cellSize, cellSize, cellSize, 10, 10);

        // Draw body segments
        g.setColor(snakeColor);
        for (int i = 1; i < snake.getBody().size(); i++) {
            Point segment = snake.getBody().get(i);
            g.fillRoundRect(segment.x * cellSize, segment.y * cellSize, cellSize, cellSize, 5, 5);
        }

        // Draw eyes on head (only if cellSize is big enough)
        if (cellSize > 10) {
            g.setColor(Color.WHITE);
            int eyeSize = cellSize / 5;
            int eyeOffset = cellSize / 4;

            switch (snake.getDirection()) {
                case "UP", "DOWN" -> {
                    g.fillOval(head.x * cellSize + eyeOffset, head.y * cellSize + eyeOffset, eyeSize, eyeSize);
                    g.fillOval(head.x * cellSize + 3*eyeOffset, head.y * cellSize + eyeOffset, eyeSize, eyeSize);
                }
                case "LEFT", "RIGHT" -> {
                    g.fillOval(head.x * cellSize + eyeOffset, head.y * cellSize + eyeOffset, eyeSize, eyeSize);
                    g.fillOval(head.x * cellSize + eyeOffset, head.y * cellSize + 3*eyeOffset, eyeSize, eyeSize);
                }
            }
        }
    }

    // Draws all food items on the game board
    private void drawFood(Graphics g) {
        g.setColor(Color.RED);
        for (Food food : foods) {
            Point pos = food.getPosition();
            int margin = cellSize/4;
            g.fillOval(pos.x * cellSize + margin, pos.y * cellSize + margin,
                    cellSize - 2*margin, cellSize - 2*margin);

            // Food detail
            g.setColor(new Color(180, 0, 0));
            g.drawLine(pos.x * cellSize + margin, pos.y * cellSize + cellSize/2,
                    pos.x * cellSize + cellSize - margin, pos.y * cellSize + cellSize/2);
        }
    }

    // Converts hex color string to Color object
    private Color parseColor(String colorHex) {
        try {
            return Color.decode(colorHex.startsWith("#") ? colorHex : "#" + colorHex);
        } catch (NumberFormatException e) {
            return Color.GREEN;
        }
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }
}