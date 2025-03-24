package player;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private static List<Player> players = new ArrayList<>();
    private static Player currentPlayer;

    public static void addPlayer(String name) {
        Player player = new Player(name);
        players.add(player);
        currentPlayer = player;
    }

    public static void updateCurrentPlayerScore(int score) {
        if (currentPlayer != null) {
            currentPlayer.addScore(score);
            currentPlayer.incrementGamesPlayed();
        }
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static List<Player> getAllPlayers() {
        return new ArrayList<>(players);
    }

    public static void displayAllPlayers() {
        System.out.println("\n=== LISTE DES JOUEURS ===");
        for (Player p : players) {
            System.out.println(p);
        }
    }
}