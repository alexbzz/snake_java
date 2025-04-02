package player;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private static List<Player> players = new ArrayList<>(); // List of all registered players
    private static Player currentPlayer; // The player currently playing

    // Adds a new player and sets them as the current player
    public static void addPlayer(String name) {
        Player player = new Player(name);
        players.add(player);
        currentPlayer = player;
    }

    // Updates the score of the current player and increments games played
    public static void updateCurrentPlayerScore(int score) {
        if (currentPlayer != null) {
            currentPlayer.addScore(score);
            currentPlayer.incrementGamesPlayed();
        }
    }

    // Returns the currently active player
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    // Returns a list of all registered players
    public static List<Player> getAllPlayers() {
        return new ArrayList<>(players); // Returns a copy to avoid external modifications
    }

    // Displays all players and their stats in the console
    public static void displayAllPlayers() {
        System.out.println("\n=== PLAYER LIST ===");
        for (Player p : players) {
            System.out.println(p);
        }
    }
}
