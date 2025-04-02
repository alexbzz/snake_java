package view;

import entities.Snake;
import player.PlayerManager;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LoginWindow extends JFrame {
    private final Map<String, Color> colorOptions = new HashMap<>();
    private Color selectedColor = Color.GREEN; // Couleur par défaut

    public LoginWindow() {
        // Configuration des options de couleur
        colorOptions.put("Vert", Color.GREEN);
        colorOptions.put("Bleu", Color.BLUE);
        colorOptions.put("Rouge", Color.RED);
        colorOptions.put("Jaune", Color.YELLOW);
        colorOptions.put("Magenta", Color.MAGENTA);
        colorOptions.put("Orange", Color.ORANGE);

        setTitle("Inscription du joueur");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        // Panel pour le nom
        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Votre nom:");
        JTextField nameField = new JTextField(15);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        // Panel pour la couleur
        JPanel colorPanel = new JPanel();
        JLabel colorLabel = new JLabel("Couleur du serpent:");
        JComboBox<String> colorCombo = new JComboBox<>(colorOptions.keySet().toArray(new String[0]));
        colorCombo.addActionListener(e -> {
            String selected = (String) colorCombo.getSelectedItem();
            selectedColor = colorOptions.get(selected);
        });
        colorPanel.add(colorLabel);
        colorPanel.add(colorCombo);

        // Aperçu de la couleur
        JPanel previewPanel = new JPanel();
        JLabel previewLabel = new JLabel("Aperçu:");
        JPanel colorPreview = new JPanel();
        colorPreview.setBackground(selectedColor);
        colorPreview.setPreferredSize(new Dimension(30, 30));
        colorCombo.addActionListener(e -> colorPreview.setBackground(selectedColor));
        previewPanel.add(previewLabel);
        previewPanel.add(colorPreview);

        // Bouton de démarrage
        JButton startButton = new JButton("Commencer le jeu");
        startButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                PlayerManager.addPlayer(name);
                startGame(selectedColor);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un nom valide");
            }
        });

        // Ajout des composants
        add(namePanel);
        add(colorPanel);
        add(previewPanel);
        add(startButton);

        setVisible(true);
    }

    private void startGame(Color snakeColor) {
        // Convertir la couleur en String hex pour le Snake
        String colorHex = String.format("#%02x%02x%02x",
                selectedColor.getRed(),
                selectedColor.getGreen(),
                selectedColor.getBlue());

        Point startPos = new Point(5, 5);
        Snake snake = new Snake(startPos, 1, colorHex, "default");
        SwingUtilities.invokeLater(() -> new GameWindow(snake));
    }

    public static void main(String[] args) {
        new LoginWindow();
    }
}