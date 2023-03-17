package com.cricketGame.cricketgame.abstractDesign;

import com.cricketGame.cricketgame.enums.TypeOfMatch;

public class MatchTypeFactory extends AbstractFactory{
    @Override
    public MatchType getMatchType(TypeOfMatch matchType) {
        if(matchType==TypeOfMatch.ODI)
        {
            return new ODI();
        }
        else if(matchType==TypeOfMatch.T20)
        {
            return new T20();
        }
        return null;
    }
}
