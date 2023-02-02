import java.util.ArrayList;
import java.util.Scanner;

public class Match {
    static Match match;
    private int totalOver=0;
    public int getTotalOver()
    {
        return this.totalOver;
    }
    public void setTotalOver(int over)
    {
        this.totalOver=over;
    }
    private Match()
    {}
    void startMatch()
    {
        Team team1=new Team("India");
        Team team2=new Team("Pakistan");
        Scanner obj=new Scanner(System.in);
        System.out.println("Enter Total Number of Overs? ");
        match.setTotalOver(Integer.parseInt(obj.nextLine()));
        System.out.println("Enter the Toss Chosen by Team1?(0/1) ");
        Team battingTeam,ballingTeam;
        if(match.Toss(Integer.parseInt(obj.nextLine())))
        {
            battingTeam=team1;
            ballingTeam=team2;
        }
        else
        {
            battingTeam=team2;
            ballingTeam=team1;
        }
        System.out.println("Team Name"+"     "+"Batting Rating" +"     "+"Balling Rating");
        System.out.println("Team "+battingTeam.TeamName+":");
        for(int i=0;i<11;i++)
        {
            System.out.println(battingTeam.players.get(i).PlayerName+"         "+battingTeam.players.get(i).getBatsManRating()+"          "+battingTeam.players.get(i).getBallerRating());
        }
        System.out.println("Team "+ballingTeam.TeamName+":");
        for(int i=0;i<11;i++)
        {
            System.out.println(ballingTeam.players.get(i).PlayerName+"          "+ballingTeam.players.get(i).getBatsManRating()+"          "+ballingTeam.players.get(i).getBallerRating());
        }
        match.matchPlaying(battingTeam,ballingTeam);
        match.matchPlaying(ballingTeam,battingTeam);
        if(battingTeam.getRuns()>ballingTeam.getRuns())
        {
            System.out.println(battingTeam.TeamName+" won the Match");
        }
        else if(battingTeam.getRuns()<ballingTeam.getRuns())
        {
            System.out.println(ballingTeam.TeamName+" won the Match");
        }
        else
        {
            System.out.println("Match Draws.");
        }
    }
    public void matchPlaying(Team battingTeam,Team ballingTeam)
    {
        int batsmanIdx=0,ballerIdx=0;
        for(int i=0;i<this.totalOver;i++)
        {
            for(int j=0;j<6;j++)
            {
                Object r=match.RandomBall(battingTeam.players.get(batsmanIdx).getBatsManRating(),ballingTeam.players.get(ballerIdx).getBallerRating());
                battingTeam.players.get(batsmanIdx).addBalls();
                if(r.equals('W'))
                {
                    ballingTeam.players.get(ballerIdx).addWicket();
                    batsmanIdx++;
                }
                else
                {
                    battingTeam.addRuns((int)r);
                    battingTeam.players.get(batsmanIdx).addRuns((int)r);
                }
                if((i*6+j)%24==0)
                {
                    ballerIdx++;
                }
                if(batsmanIdx>=battingTeam.players.size())
                {
                    break;
                }
            }
            System.out.println("Over"+(i+1)+": "+battingTeam.TeamName+" "+battingTeam.getRuns()+"/"+batsmanIdx);
            if(batsmanIdx>=battingTeam.players.size())
            {
                break;
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }
    public static Match matchInstance()
    {
        if(match==null)
        {
            match=new Match();
        }
        return match;
    }
    boolean Toss(int team1){
        int result=(int)(Math.random()*2)%2;
        return result==team1;
    }

    public Object RandomBall(int batsmanRating,int ballerRating)
    {
        ArrayList<Object>nums=new ArrayList<Object>();
        for(int i=0;i<=6;i++)
        {
            nums.add(i);
        }
        nums.add('W');
        if(batsmanRating>ballerRating)
        {
            nums.add(4);
            nums.add(6);
            if(batsmanRating-ballerRating>=5)
            {
                nums.add(4);
                nums.add(6);
            }
        }
        else if(batsmanRating<ballerRating)
        {
            nums.add(1);
            nums.add(2);
            nums.add(0);
            nums.add(3);
            if(ballerRating-batsmanRating>=5)
            {
                nums.add('W');
                nums.add('W');
            }
        }
        int currIdx=(int)(Math.random()*nums.size());
        return nums.get(currIdx);
    }

}
