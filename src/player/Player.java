package player;

public class Player {
    private String name; // Player's name
    private int score; // Current score of the player
    private int gamesPlayed; // Number of games the player has played

    // Constructor: Initializes the player with a name and default values
    public Player(String name) {
        this.name = name;
        this.score = 0; // Initial score set to 0
        this.gamesPlayed = 0; // No games played initially
    }

    // Getters
    public String getName() { return name; }
    public int getScore() { return score; }
    public int getGamesPlayed() { return gamesPlayed; }

    // Methods to manage the score

    // Adds points to the player's score
    public void addScore(int points) {
        this.score += points;
    }

    // Resets the player's score to 0
    public void resetScore() {
        this.score = 0;
    }

    // Increments the number of games played
    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    // Returns a formatted string with player details
    @Override
    public String toString() {
        return String.format("Player: %s | Score: %d | Games Played: %d",
                name, score, gamesPlayed);
    }
}
