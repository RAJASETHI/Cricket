package com.cricketGame.cricketgame.abstractDesign;

import com.cricketGame.cricketgame.enums.TypeOfMatch;
import com.cricketGame.cricketgame.model.Match;

public class T20 implements MatchType{
    @Override
    public void setMatchDetails(Match match) {
        match.setTotalOver(20);
        match.setMatchType(TypeOfMatch.T20);
    }
}
