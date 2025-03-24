package view;

import objects.Food;
import entities.Snake;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameLayer extends JPanel {

    private Snake snake;
    private List<Food> foods;
    private int cellSize;

    public GameLayer(Snake snake, List<Food> foods, int cellSize) {
        this.snake = snake;
        this.foods = foods;
        this.cellSize = cellSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSnake(g);
        drawFood(g);
    }

    private void drawSnake(Graphics g) {
        g.setColor(Color.GREEN);
        List<Point> body = snake.getBody();
        // Affiche chaque segment du serpent
        for (Point segment : body) {
            g.fillRect(segment.x * cellSize, segment.y * cellSize, cellSize, cellSize);
        }
    }

    private void drawFood(Graphics g) {
        g.setColor(Color.RED);
        for (Food food : foods) {
            Point pos = food.getPosition();
            g.fillOval(pos.x * cellSize, pos.y * cellSize, cellSize, cellSize);
        }
    }
}
