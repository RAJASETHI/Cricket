import java.util.ArrayList;

public class Team{
    String TeamName;
    ArrayList<Player> players;
    private int runs=0;
    void addRuns(int run)
    {
        this.runs+=run;
    }
    public int getRuns()
    {
        return this.runs;
    }
    static int RandomRating()
    {
        return ((int)(Math.random()*11))%10;
    }
    Team(String name)
    {
        this.TeamName=name;
        players=new ArrayList<>();
        for(int i = 1; i <= 11; i++)
        {
            /** Adding the User Input the Name of the Players **/
//            Scanner obj=new Scanner(System.in);
//            String PlayerName=obj.nextLine();
            String PlayerName="player "+i;
            players.add(new Player(PlayerName,TeamName,Team.RandomRating(),Team.RandomRating()));
        }
    }


}