import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class InningScoreCardOfTheMatch {
    Team team;
    private int TeamRunsOfThisMatch = 0;
    private int TeamBallsPlayedOfThisMatch=0;
    /**
     * {player,{runs,balls}}
     **/
    private final HashMap<Baller, ArrayList<Integer>> BatsmenScoreCard;
    /**
     * {player,wicket}
     **/
    private final HashMap<Baller, Integer> BallersScoreCard;

    InningScoreCardOfTheMatch(Team team) {
        this.team = team;
        BatsmenScoreCard = new HashMap<>();
        BallersScoreCard = new HashMap<>();
    }

    public int getTeamRunsOfThisMatch() {
        return this.TeamRunsOfThisMatch;
    }

    public void addCurrentRuns(int runs) {
        this.TeamRunsOfThisMatch += runs;
    }

    public void addCurrentBallsPlayed() {
        this.TeamBallsPlayedOfThisMatch++;
    }

    public void addWicketOfPlayer(Baller player) {
        BallersScoreCard.put(player, BallersScoreCard.getOrDefault(player, 0) + 1);
    }

    public void addRunOfPlayer(Baller player, int runs) {
        if (BatsmenScoreCard.containsKey(player)) {
            int r = BatsmenScoreCard.get(player).get(0) + runs;
            int b = BatsmenScoreCard.get(player).get(1) + 1;
            BatsmenScoreCard.put(player, new ArrayList<>(Arrays.asList(r, b)));
        } else {
            BatsmenScoreCard.put(player, new ArrayList<>(Arrays.asList(runs, 1)));
        }
    }
}
