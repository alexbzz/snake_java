package view;

import javax.swing.*;
import java.awt.*;

import entities.Snake;
import player.PlayerManager;

public class LoginWindow extends JFrame {
    public LoginWindow() {
        setTitle("Entrez votre nom");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel label = new JLabel("Votre nom:", SwingConstants.CENTER);
        JTextField nameField = new JTextField();
        JButton startButton = new JButton("Commencer");

        startButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                PlayerManager.addPlayer(name);
                dispose();
                new GameWindow(new Snake(new Point(5, 5), 1, "green", "default"));
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un nom valide");
            }
        });

        panel.add(label);
        panel.add(nameField);
        panel.add(startButton);
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginWindow();
    }
}