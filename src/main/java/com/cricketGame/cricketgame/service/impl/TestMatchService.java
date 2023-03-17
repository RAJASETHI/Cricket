package com.cricketGame.cricketgame.service.impl;

import com.cricketGame.cricketgame.Request.MatchRequest;
import com.cricketGame.cricketgame.Response.MatchResponse;
import com.cricketGame.cricketgame.model.Match;
import com.cricketGame.cricketgame.service.IMatchInterface;
import com.cricketGame.cricketgame.service.MatchServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TestMatchService implements IMatchInterface {

    @Autowired
    private MatchServices matchServices;
    private final int NUMBER_OF_OVERS = 450;
    @Override
    public MatchResponse startMatch(MatchRequest matchRequest) {
        Match match = new Match();
        match.setMatchId(UUID.randomUUID().toString());
        match.setTeam1Id(matchRequest.getTeam1Id());
        match.setTeam2Id(matchRequest.getTeam2Id());
        match.setTossChosenByTeam1(matchRequest.getTossChosenByTeam1());
        System.out.println("Inside Test Match");
        return new MatchResponse();
    }
}
