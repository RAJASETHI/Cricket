package com.cricketGame.cricketgame.mclass;

public class MatchTypeFactory extends AbstractFactory{
    @Override
    MatchType getMatchType(String matchType) {
        if(matchType.equalsIgnoreCase("ODI"))
        {
            return new ODI();
        }
        else if(matchType.equalsIgnoreCase("T20"))
        {
            return new T20();
        }
        return null;
    }
}
