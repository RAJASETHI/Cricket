public class Batsman extends Player{
    Batsman(){}
    Batsman(String PlayerName,String TeamName,int batsManRating)
    {
        this.PlayerName=PlayerName;
        this.TeamName=TeamName;
        this.batsManRating=batsManRating;
    }
    private int batsManRating=-1;
    private int totalRuns=0;
    private int totalBallsPlayed =0;
    protected void setBatsManRating(int batsManRating)
    {
        this.batsManRating=batsManRating;
    }
    public int getBatsManRating()
    {
        return this.batsManRating;
    }
    void addRuns(int run)
    {
        this.totalRuns+=run;
    }
    public int getRuns()
    {
        return this.totalRuns;
    }
    void addBallsPlayed(int balls)
    {
        this.totalBallsPlayed +=balls;
    }
    public int getBallsPlayed()
    {
        return this.totalBallsPlayed;
    }

}
