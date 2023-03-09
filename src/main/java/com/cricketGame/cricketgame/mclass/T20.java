package com.cricketGame.cricketgame.mclass;

import com.cricketGame.cricketgame.Enum.TypeOfMatch;
import com.cricketGame.cricketgame.model.Match;

public class T20 implements MatchType{
    @Override
    public void setMatchDetails(Match match) {
        match.setTotalOver(20);
        match.setMatchType(TypeOfMatch.T20);
    }
}
