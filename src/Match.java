import java.util.Collections;
import java.util.Scanner;

public class Match {
    static Match match;
    public static Match matchInstance() {
        if (match == null) {
            match = new Match();
        }
        return match;
    }
    private int totalOver = 0;

    public int getTotalOver() {
        return this.totalOver;
    }

    public void setTotalOver(int over) {
        this.totalOver = over;
    }

    private Match() {
    }

    void startMatch() {
        Team team1 = new Team("India");
        Team team2 = new Team("Pakistan");
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter Total Number of Overs? ");
        match.setTotalOver(Integer.parseInt(obj.nextLine()));
        System.out.println("Enter the Toss Chosen by Team1?(0/1) ");
        InningScoreCardOfTheMatch battingTeam, ballingTeam;
        if (Toss.toss(Integer.parseInt(obj.nextLine()))) {
            battingTeam = new InningScoreCardOfTheMatch(team1);
            ballingTeam = new InningScoreCardOfTheMatch(team2);
        } else {
            battingTeam = new InningScoreCardOfTheMatch(team2);
            ballingTeam = new InningScoreCardOfTheMatch(team1);
        }
        match.inningOfTheMatch(battingTeam, ballingTeam);
        match.inningOfTheMatch(ballingTeam, battingTeam);
        if (battingTeam.getTeamRunsOfThisMatch() > ballingTeam.getTeamRunsOfThisMatch()) {
            System.out.println(battingTeam.team.teamName + " won the Match");
        } else if (battingTeam.getTeamRunsOfThisMatch() < ballingTeam.getTeamRunsOfThisMatch()) {
            System.out.println(ballingTeam.team.teamName + " won the Match");
        } else {
            System.out.println("Match Draws.");
        }
    }
    public void inningOfTheMatch(InningScoreCardOfTheMatch battingTeam, InningScoreCardOfTheMatch ballingTeam) {
        System.out.println("Match Starts");
        System.out.println(battingTeam.team.teamName + " is doing Batting against " + ballingTeam.team.teamName);
        int BattingIdx = 0, BallingIdx = 0;

        Collections.sort(battingTeam.team.playersList, new SortByBatsmanRatings());
        Collections.sort(ballingTeam.team.playersList, new SortByBowlerRatings());
        for (int i = 0; i < this.totalOver; i++) {
            for (int j = 0; j < 6; j++) {
                Bowler battingPlayer = battingTeam.team.playersList.get(BattingIdx), bowlerPlayer = ballingTeam.team.playersList.get(BallingIdx);
                Object r = match.RandomBallGenerator(battingPlayer.getBatsManRating(), bowlerPlayer.getBallerRating());
                if (r.equals('W')) {
                    bowlerPlayer.addWicket();
                    ballingTeam.addWicketOfPlayer(bowlerPlayer);
                    System.out.println(battingPlayer.playerName + " has been out against " + bowlerPlayer.playerName);
                    BattingIdx++;
                } else {
                    battingTeam.addCurrentRuns((int) r);
                    battingPlayer.addRuns((int) r);
                    battingTeam.addRunOfPlayer(battingPlayer, (int) r);
                    System.out.println(battingPlayer.playerName + " has scored " + r + " against " + bowlerPlayer.playerName);
                }
                if (BattingIdx >= battingTeam.team.playersList.size()) {
                    break;
                }
            }
            System.out.println();
            System.out.println("Over" + (i + 1) + ": " + battingTeam.team.teamName + " " + battingTeam.getTeamRunsOfThisMatch() + "/" + BattingIdx);
            if (BattingIdx >= battingTeam.team.playersList.size()) {
                break;
            }
            BallingIdx = (BallingIdx + 1) % 11;
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }





    public Object RandomBallGenerator(int batsmanRating, int ballerRating) {
        int arr[] = {0, 1, 2, 3, 4, 5, 6, 7};
        int ii = batsmanRating - ballerRating;
//        int x=ii,y=10-ii;
        int[] freq;
        if (ii > 0) {
            freq = new int[]{5, 5, 5, 5, 25, 25, 25, 5};
        } else if (ii == 0) {
            freq = new int[]{12, 13, 12, 13, 12, 13, 12, 13};
        } else {
            freq = new int[]{14, 14, 14, 14, 5, 5, 5, 29};
        }
        int result = GenerateRandom.myRand(arr, freq, 8);
        if (result == 7) return 'W';
        return result;
//
    }

    static class GenerateRandom {

        // Utility function to find ceiling of r in arr[l..h]
        static int findCeil(int arr[], int r, int l, int h) {
            int mid;
            while (l < h) {
                mid = l + ((h - l) >> 1); // Same as mid = (l+h)/2
                if (r > arr[mid])
                    l = mid + 1;
                else
                    h = mid;
            }
            return (arr[l] >= r) ? l : -1;
        }

        // The main function that returns a random number
// from arr[] according to distribution array
// defined by freq[]. n is size of arrays.
        static int myRand(int arr[], int freq[], int n) {
            // Create and fill prefix array
            int prefix[] = new int[n], i;
            prefix[0] = freq[0];
            for (i = 1; i < n; ++i)
                prefix[i] = prefix[i - 1] + freq[i];

            // prefix[n-1] is sum of all frequencies.
            // Generate a random number with
            // value from 1 to this sum
            int r = ((int) (Math.random() * (323567)) % prefix[n - 1]) + 1;

            // Find index of ceiling of r in prefix array
            int indexc = findCeil(prefix, r, 0, n - 1);
            return arr[indexc];
        }
    }

}
