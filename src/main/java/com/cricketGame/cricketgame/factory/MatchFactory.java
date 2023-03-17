package com.cricketGame.cricketgame.factory;

import com.cricketGame.cricketgame.enums.TypeOfMatch;
import com.cricketGame.cricketgame.service.IMatchInterface;
import com.cricketGame.cricketgame.service.impl.OdiMatchService;
import com.cricketGame.cricketgame.service.impl.T20MatchService;
import com.cricketGame.cricketgame.service.impl.TestMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchFactory {

    @Autowired
    private OdiMatchService odiMatchService;

    @Autowired
    private TestMatchService testMatchService;

    @Autowired
    private T20MatchService t20MatchService;

    public IMatchInterface getMatchService(TypeOfMatch matchType){
        switch (matchType){
            case ODI : return odiMatchService;
            case TEST_MATCH: return testMatchService;
            case T20:return t20MatchService;
            default: return odiMatchService;
        }
    }
}
