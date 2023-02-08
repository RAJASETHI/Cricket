public class Toss {
    public static boolean toss(int team1){
        int result=(int)(Math.random()*2)%2;
        return result==team1;
    }
}
