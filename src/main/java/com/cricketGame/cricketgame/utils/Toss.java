package com.cricketGame.cricketgame.utils;

import com.cricketGame.cricketgame.Enum.CoinSide;

public class Toss {
    // add enum.
    public static boolean toss(String team1){
        CoinSide chosen;
        if(team1.equalsIgnoreCase("HEADS"))
        {
            chosen=CoinSide.HEADS;
        }
        else
        {
            chosen=CoinSide.TAILS;
        }
        int result=(int)(Math.random()*2)%2;
        if((result==0 && chosen==CoinSide.HEADS) || (result==1 && chosen==CoinSide.TAILS))
        {
            return true;
        }
        return false;
    }
}