package game;

import entities.Snake;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Snake snake = new Snake(new Point(5, 5), 1);

        System.out.println("Pos init : " + snake.getPosition());
        snake.setDirection("RIGHT");
        snake.move();
        System.out.println("New pos : " + snake.getPosition());
    }
}
