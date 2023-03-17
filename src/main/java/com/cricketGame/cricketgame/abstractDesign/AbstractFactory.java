package com.cricketGame.cricketgame.abstractDesign;

import com.cricketGame.cricketgame.enums.TypeOfMatch;

public abstract class AbstractFactory {
    abstract MatchType getMatchType(TypeOfMatch matchType) ;
}
