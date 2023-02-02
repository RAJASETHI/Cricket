import java.util.Scanner;
class  Player{
    String name = null;
    private int balls=0;

    int wickets=0;
    int team=-1;
    int runs=0;
    // Rating Should be out of 10.
    int batsManRating=-1;
    int ballerRating=-1;

}
class Cricket{

    Cricket(){}
    public char RandomBall()
    {
        int currVal=(int)(Math.random()*8);
        if(currVal==7)return 'W';
        return (char)('0'+currVal);
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting the Game of Cricket");
        System.out.print("Enter Team 1 Name? ");
        Scanner obj=new Scanner(System.in);
        String team1=obj.nextLine();
//        String team 2=

    }
}