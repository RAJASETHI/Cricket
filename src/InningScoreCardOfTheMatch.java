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
    private final HashMap<Bowler, ArrayList<Integer>> BatsmenScoreCard;
    /**
     * {player,wicket}
     **/
    private final HashMap<Bowler, Integer> BallersScoreCard;

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

    public void addWicketOfPlayer(Bowler player) {
        BallersScoreCard.put(player, BallersScoreCard.getOrDefault(player, 0) + 1);
    }

    public void addRunOfPlayer(Bowler player, int runs) {
        if (BatsmenScoreCard.containsKey(player)) {
            int r = BatsmenScoreCard.get(player).get(0) + runs;
            int b = BatsmenScoreCard.get(player).get(1) + 1;
            BatsmenScoreCard.put(player, new ArrayList<>(Arrays.asList(r, b)));
        } else {
            BatsmenScoreCard.put(player, new ArrayList<>(Arrays.asList(runs, 1)));
        }
    }
}
