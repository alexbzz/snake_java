package view;

import objects.Food;
import entities.Snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWindow extends JFrame {

    private int rows;
    private int cols;
    private int cellSize = 30;
    private Snake snake;
    private List<Food> foods; // Liste des aliments

    public GameWindow(Snake snake) {
        this.snake = snake;
        this.foods = new ArrayList<>();

        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        add(new GamePanel());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });

        setVisible(true);
        requestFocusInWindow();
    }

    public void generateFoods(int foodCount) {
        //if (cols <= 0 || rows <= 0) {
            //System.err.println("Erreur: Dimensions du plateau invalides (cols=" + cols + ", rows=" + rows + ")");
            //return; // Empêche l'ajout de nourriture si la grille n'est pas encore prête
        //}

        Random random = new Random();
        for (int i = 0; i < foodCount; i++) {
            int x = random.nextInt(cols); // cols > 0 garanti par le check au-dessus
            int y = random.nextInt(rows);
            foods.add(new Food(new Point(x, y), 1, "red", "apple"));
        }
    }


    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension size = getSize();
            cols = size.width / cellSize;
            rows = size.height / cellSize;

            drawGrid(g);
            drawSnake(g);
            generateFoods(1);
            drawFood(g);
            direction(g);
        }

        private void drawGrid(Graphics g) {
            g.setColor(Color.LIGHT_GRAY);
            for (int i = 0; i <= rows; i++) {
                g.drawLine(0, i * cellSize, getWidth(), i * cellSize);
            }

            for (int j = 0; j <= cols; j++) {
                g.drawLine(j * cellSize, 0, j * cellSize, getHeight());
            }
        }

        private void drawSnake(Graphics g) {
            g.setColor(Color.GREEN);
            List<Point> body = snake.getBody();
            /*for (Point segment : body) {
                g.fillRect(segment.x * cellSize, segment.y * cellSize, cellSize, cellSize);
            }*/
            g.fillRect(snake.getPosition().x, snake.getPosition().y, cellSize, cellSize);
        }

        private void drawFood(Graphics g) {
            g.setColor(Color.RED);
            for (Food food : foods) {
                Point pos = food.getPosition();
                g.fillOval(pos.x * cellSize, pos.y * cellSize, cellSize, cellSize);
            }
        }

        public void direction (Graphics g) {
            Timer timer = new Timer(1000, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    snake.getPosition().x += cellSize;
                    drawSnake(g);
                    getRootPane().repaint();
                }
            });
            timer.start();
        }
    }

    public static void main(String[] args) {
        Snake snake = new Snake(new Point(5, 5), 1, "green", "default");
        GameWindow gameWindow = new GameWindow(snake);
        //gameWindow.generateFoods(5); // Génère 5 aliments aléatoirement
    }
}
