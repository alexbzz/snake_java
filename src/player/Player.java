package player;

public class Player {
    private String name;
    private int score;
    private int gamesPlayed;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.gamesPlayed = 0;
    }

    // Getters
    public String getName() { return name; }
    public int getScore() { return score; }
    public int getGamesPlayed() { return gamesPlayed; }

    // Méthodes pour gérer le score
    public void addScore(int points) {
        this.score += points;
    }

    public void resetScore() {
        this.score = 0;
    }

    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    @Override
    public String toString() {
        return String.format("Joueur: %s | Score: %d | Parties jouées: %d",
                name, score, gamesPlayed);
    }
}