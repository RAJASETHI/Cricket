
public class  Player{
    String PlayerName;
    String TeamName;
    private int batsManRating=-1;
    private int ballerRating=-1;
    private int balls=0;
    private int wickets=0;
    private int runs=0;
    Player(String PlayerName,String TeamName,int batsManRating,int ballerRating)
    {
        this.PlayerName=PlayerName;
        this.TeamName=TeamName;
        this.batsManRating=batsManRating;
        this.ballerRating=ballerRating;
    }

    public int getBatsManRating()
    {
        return this.batsManRating;
    }
    public int getBallerRating()
    {
        return this.ballerRating;
    }
    void addBalls()
    {
        this.balls++;
    }
    public int getBalls()
    {
        return this.balls;
    }

    void addWicket()
    {
        this.wickets++;
    }
    public int getWickets()
    {
        return this.wickets;
    }
    void addRuns(int run)
    {
        this.runs+=run;
    }
    public int getRuns()
    {
        return this.runs;
    }

    // Rating Should be out of 10.

}