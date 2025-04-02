package view;

import objects.Food;
import entities.Snake;
import player.Player;
import player.PlayerManager;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWindow extends JFrame {
    private final int rows = 20;
    private final int cols = 20;
    private final int cellSize = 30;
    private final Snake snake;
    private final List<Food> foods;
    private final Timer timer;
    private final JLabel scoreLabel;
    private boolean justAte = false;

    public GameWindow(Snake snake) {
        this.snake = snake;
        this.foods = new ArrayList<>();

        setTitle("Snake Game - " + PlayerManager.getCurrentPlayer().getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Calculate exact window size
        int gridWidth = cols * cellSize;
        int gridHeight = rows * cellSize;
        int scorePanelHeight = 50;
        setSize(gridWidth, gridHeight + scorePanelHeight);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true); // Remove title bar and borders

        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel setup
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY); // Dark background for better grid visibility

        // Score panel setup
        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(new Color(50, 50, 50));
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
        scorePanel.add(scoreLabel);
        mainPanel.add(scorePanel, BorderLayout.NORTH);

        // Game panel with grid
        GamePanel gamePanel = new GamePanel();
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        // Create a panel for the image and add it to the right
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        JLabel imageLabel = new JLabel();
        try {
            // Load the image from the path
            Image image = ImageIO.read(new File("C:\\Users\\alexb\\Documents\\java\\snake_java\\resources\\lostralaleritos.jpg"));
            imageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Add the image panel to the right side of the main panel
        mainPanel.add(imagePanel, BorderLayout.EAST);

        add(mainPanel);

        // Key controls
        addKeyListener(new Keyboard(snake));

        // Generate food
        generateFoods();

        // Game timer
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

    private void generateFoods() {
        Random random = new Random();
        Point foodPos;
        boolean validPosition;

        do {
            foodPos = new Point(random.nextInt(cols), random.nextInt(rows));
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

    private void checkCollisions() {
        Point head = snake.getBody().getFirst();

        // Collision with food
        for (Food food : new ArrayList<>(foods)) {
            if (head.equals(food.getPosition())) {
                snake.grow(1);
                foods.remove(food);
                generateFoods();
                PlayerManager.updateCurrentPlayerScore(1);
                justAte = true;
                break;
            }
        }

        if (!justAte) {
            // Collision with walls
            if (head.x < 0 || head.x >= cols || head.y < 0 || head.y >= rows) {
                gameOver();
            }
            // Collision with itself
            for (int i = 1; i < snake.getBody().size(); i++) {
                if (head.equals(snake.getBody().get(i))) {
                    gameOver();
                }
            }
        }
    }

    private void updateScoreDisplay() {
        Player current = PlayerManager.getCurrentPlayer();
        scoreLabel.setText("Player: " + current.getName() + " | Score: " + current.getScore());
    }

    private void gameOver() {
        timer.stop();
        Player current = PlayerManager.getCurrentPlayer();

        String message = "Game Over\n\n" +
                "Player: " + current.getName() + "\n" +
                "Final score: " + current.getScore() + "\n" +
                "Food eaten: " + current.getGamesPlayed();

        JOptionPane.showMessageDialog(this, message);
        PlayerManager.displayAllPlayers();
        System.exit(0);
    }

    private class GamePanel extends JPanel {
        public GamePanel() {
            setBackground(new Color(30, 30, 30)); // Dark background for the game
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawGrid(g);
            new GameLayer(snake, foods, cellSize).paintComponent(g);
        }

        private void drawGrid(Graphics g) {
            // Draw a visible grid
            g.setColor(new Color(80, 80, 80)); // Dark gray for the grid lines

            // Horizontal lines
            for (int i = 0; i <= rows; i++) {
                g.drawLine(0, i * cellSize, cols * cellSize, i * cellSize);
            }

            // Vertical lines
            for (int j = 0; j <= cols; j++) {
                g.drawLine(j * cellSize, 0, j * cellSize, rows * cellSize);
            }

            // Highlight every 5th line
            g.setColor(new Color(120, 120, 120));
            for (int i = 0; i <= rows; i += 5) {
                g.drawLine(0, i * cellSize, cols * cellSize, i * cellSize);
            }
            for (int j = 0; j <= cols; j += 5) {
                g.drawLine(j * cellSize, 0, j * cellSize, rows * cellSize);
            }
        }
    }

    public static void main(String[] args) {
        // Entry point (not used when launched from LoginWindow)
    }
}
