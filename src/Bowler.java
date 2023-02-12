import java.util.Comparator;

public class Bowler extends Batsman{

    Bowler(String PlayerName, String TeamName, int batsManRating, int ballerRating)
    {
        this.playerName =PlayerName;
        this.teamName =TeamName;
        this.ballerRating=batsManRating;
        super.setBatsManRating(batsManRating);
    }
    private int ballerRating=-1;
    private int wickets=0;
    public int getBallerRating()
    {
        return this.ballerRating;
    }
    void addWicket()
    {
        this.wickets++;
    }
    public int getWickets()
    {
        return this.wickets;
    }
}
class SortByBowlerRatings implements Comparator<Bowler> {
    public int compare(Bowler a, Bowler b)
    {
        return b.getBallerRating()-a.getBallerRating();
    }
}

class SortByBatsmanRatings implements Comparator<Bowler> {
    public int compare(Bowler a, Bowler b)
    {
        return b.getBatsManRating()-a.getBatsManRating();
    }
}