package com.cricketGame.cricketgame.abstractDesign;

import com.cricketGame.cricketgame.enums.TypeOfMatch;
import com.cricketGame.cricketgame.model.Match;

public class ODI implements MatchType{

    @Override
    public void setMatchDetails(Match match) {
        match.setTotalOver(50);
        match.setMatchType(TypeOfMatch.ODI);
    }
}
