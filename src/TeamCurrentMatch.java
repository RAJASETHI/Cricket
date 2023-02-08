import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TeamCurrentMatch {
    Team team;
    private int currentRuns = 0;
    private int currentBallsPlayed;
    /**
     * {player,{runs,balls}}
     **/
    private final HashMap<Baller, ArrayList<Integer>> Batsman;
    /**
     * {player,wicket}
     **/
    private final HashMap<Baller, Integer> Baller;

    TeamCurrentMatch(Team team) {
        this.team = team;
        Batsman = new HashMap<>();
        Baller = new HashMap<>();
    }

    public int getCurrentRuns() {
        return this.currentRuns;
    }

    public void addCurrentRuns(int runs) {
        this.currentRuns += runs;
    }

    public void addCurrentBallsPlayed() {
        this.currentBallsPlayed++;
    }

    public void addWicketOfPlayer(Baller player) {
        Baller.put(player, Baller.getOrDefault(player, 0) + 1);
    }

    public void addRunOfPlayer(Baller player, int runs) {
        if (Batsman.containsKey(player)) {
            int r = Batsman.get(player).get(0) + runs;
            int b = Batsman.get(player).get(1) + 1;
            Batsman.put(player, new ArrayList<>(Arrays.asList(r, b)));
        } else {
            Batsman.put(player, new ArrayList<>(Arrays.asList(runs, 1)));
        }
    }
}
