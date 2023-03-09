package com.cricketGame.cricketgame.mclass;

import com.cricketGame.cricketgame.Enum.TypeOfMatch;
import com.cricketGame.cricketgame.model.Match;

public class ODI implements MatchType{

    @Override
    public void setMatchDetails(Match match) {
        match.setTotalOver(50);
        match.setMatchType(TypeOfMatch.ODI);
    }
}
