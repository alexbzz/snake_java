package game;

import javax.swing.*;

public static void main(String[] args) {
    // Au lieu de lancer directement le jeu
    SwingUtilities.invokeLater(() -> new LoginWindow());
}