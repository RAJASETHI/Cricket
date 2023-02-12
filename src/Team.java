import java.util.ArrayList;

public class Team {
    String teamName;
    ArrayList<Bowler> playersList;

    static int RandomRating() {
        return ((int) (Math.random() * 11)) % 10;
    }

    Team(String name) {
        this.teamName = name;
        playersList = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            String PlayerName = "player" + i;
            playersList.add(new Bowler(PlayerName, teamName, Team.RandomRating(), Team.RandomRating()));
        }
    }
}