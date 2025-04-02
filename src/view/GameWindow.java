package view;

import objects.Food;
import entities.Snake;
import player.Player;
import player.PlayerManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWindow extends JFrame {

    private int rows = 20;
    private int cols = 20;
    private int cellSize = 30;
    private Snake snake;
    private List<Food> foods;
    private Timer timer;
    private JLabel scoreLabel;
    private boolean justAte = false;

    public GameWindow(Snake snake) {
        this.snake = snake;
        this.foods = new ArrayList<>();

        setTitle(STR."Snake Game - \{PlayerManager.getCurrentPlayer().getName()}");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Panel principal avec BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel du score en haut
        JPanel scorePanel = new JPanel();
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scorePanel.add(scoreLabel);
        mainPanel.add(scorePanel, BorderLayout.NORTH);

        // Panel de jeu au centre
        GamePanel gamePanel = new GamePanel();
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        add(mainPanel);

        // Contrôles clavier
        Keyboard keyboard = new Keyboard(snake);
        addKeyListener(keyboard);

        // Génération nourriture
        generateFoods(1);

        // Timer du jeu
        timer = new Timer(200, e -> {
            snake.move();
            checkCollisions();
            updateScoreDisplay();
            justAte = false;
            gamePanel.repaint();
        });
        timer.start();

        setVisible(true);
        requestFocusInWindow();
    }

    private void generateFoods(int foodCount) {
        Random random = new Random();
        for (int i = 0; i < foodCount; i++) {
            Point foodPos;
            boolean validPosition;

            do {
                int x = random.nextInt(cols);
                int y = random.nextInt(rows);
                foodPos = new Point(x, y);

                validPosition = true;
                for (Point segment : snake.getBody()) {
                    if (segment.equals(foodPos)) {
                        validPosition = false;
                        break;
                    }
                }
            } while (!validPosition);

            foods.add(new Food(foodPos, 1, "red", "apple"));
        }
    }

    private void checkCollisions() {
        Point head = snake.getBody().getFirst();

        // Collision avec nourriture
        for (Food food : new ArrayList<>(foods)) {
            if (head.equals(food.getPosition())) {
                snake.grow(1);
                foods.remove(food);
                generateFoods(1);
                PlayerManager.updateCurrentPlayerScore(10); // +10 points
                justAte = true;
                break;
            }
        }

        if (!justAte) {
            // Mur
            if (head.x < 0 || head.x >= cols || head.y < 0 || head.y >= rows) {
                gameOver();
            }

            // Corps
            for (int i = 1; i < snake.getBody().size(); i++) {
                if (head.equals(snake.getBody().get(i))) {
                    gameOver();
                }
            }
        }
    }

    private void updateScoreDisplay() {
        Player current = PlayerManager.getCurrentPlayer();
        scoreLabel.setText("Joueur: " + current.getName() + " | Score: " + current.getScore());
    }

    private void gameOver() {
        timer.stop();
        Player current = PlayerManager.getCurrentPlayer();

        String message = STR."""
Game Over

Joueur: \{current.getName()}
Score final: \{current.getScore()}
Nouriture avaler: \{current.getGamesPlayed()}""";

        JOptionPane.showMessageDialog(this, message);
        PlayerManager.displayAllPlayers();
        System.exit(0);
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawGrid(g);
            new GameLayer(snake, foods, cellSize).paintComponent(g);
        }

        private void drawGrid(Graphics g) {
            g.setColor(new Color(240, 240, 240));
            for (int i = 0; i <= rows; i++) {
                g.drawLine(0, i * cellSize, cols * cellSize, i * cellSize);
            }
            for (int j = 0; j <= cols; j++) {
                g.drawLine(j * cellSize, 0, j * cellSize, rows * cellSize);
            }
        }
    }

    public static void main(String[] args) {
    }
}