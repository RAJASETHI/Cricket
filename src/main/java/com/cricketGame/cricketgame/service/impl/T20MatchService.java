package com.cricketGame.cricketgame.service.impl;

import com.cricketGame.cricketgame.request.MatchRequest;
import com.cricketGame.cricketgame.response.MatchResponse;
import com.cricketGame.cricketgame.model.Match;
import com.cricketGame.cricketgame.model.Player;
import com.cricketGame.cricketgame.repository.MatchRepository;
import com.cricketGame.cricketgame.service.IMatchInterface;
import com.cricketGame.cricketgame.service.MatchServices;
import com.cricketGame.cricketgame.service.TeamServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public class T20MatchService implements IMatchInterface {


    @Autowired
    private MatchServices matchServices;
    @Autowired
    private TeamServices teamServices;
    @Autowired
    private MatchRepository matchRepository;
    private final int NUMBER_OF_OVERS = 20;
    @Override
    public MatchResponse startMatch(MatchRequest matchRequest) {
        Match match = new Match();

        match.setMatchType(matchRequest.getMatchType());
        match.setMatchId(UUID.randomUUID().toString());
        match.setTeam1Id(matchRequest.getTeam1Id());
        match.setTeam2Id(matchRequest.getTeam2Id());
        match.setTossChosenByTeam1(matchRequest.getTossChosenByTeam1());
        match.setTotalOver(NUMBER_OF_OVERS);
        match.setTeam1InningScoreCard(new ArrayList<>());
        match.setTeam2InningScoreCard(new ArrayList<>());
        System.out.println("Inside T20 Match");
        matchServices.doTossService(match);
        ArrayList<Player> battingTeamPlayerList, bowlerTeamPlayerList;
        battingTeamPlayerList = teamServices.showPlayersOfaTeam(match.getTeam1Id());
        bowlerTeamPlayerList = teamServices.showPlayersOfaTeam(match.getTeam2Id());
        int targetRuns = matchServices.inning(match, battingTeamPlayerList, bowlerTeamPlayerList, Integer.MAX_VALUE);

        int finalScore = matchServices.inning(match, bowlerTeamPlayerList, battingTeamPlayerList, targetRuns);

        String res=matchServices.getResultOfMatch(match, targetRuns, finalScore);
        matchRepository.save(match);
        return matchServices.toMatchResponse(match);
    }
}
