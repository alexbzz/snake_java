package view;

import entities.Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter {
    private final Snake snake;

    public Keyboard(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Changer la direction du serpent en fonction de la touche appuyée
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                snake.changeDirection("UP");
                break;
            case KeyEvent.VK_DOWN:
                snake.changeDirection("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                snake.changeDirection("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                snake.changeDirection("RIGHT");
                break;
        }
    }
}