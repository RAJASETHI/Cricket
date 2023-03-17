package com.cricketGame.cricketgame.controller;

import com.cricketGame.cricketgame.Request.MatchRequest;
import com.cricketGame.cricketgame.Response.MatchResponse;
import com.cricketGame.cricketgame.enums.TypeOfMatch;
import com.cricketGame.cricketgame.factory.MatchFactory;
import com.cricketGame.cricketgame.abstractDesign.MatchType;
import com.cricketGame.cricketgame.abstractDesign.MatchTypeFactory;
import com.cricketGame.cricketgame.model.Match;
import com.cricketGame.cricketgame.service.IMatchInterface;
import com.cricketGame.cricketgame.service.MatchServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/match")
public class MatchController {
    @Autowired
    private MatchServices matchServices;

    @Autowired
    private MatchFactory matchFactory;
/*
//    @PostMapping(value = "/start")
//     TODO Remove DB Entities from request , response
    // Always return new Request, Response classes specific
//    public Match goToCreateMatch(@RequestBody MatchRequest match) throws Exception {
//        //Team1,Team2,matchType
//        match.setMatchId(UUID.randomUUID().toString());
//        MatchTypeFactory matchTypeFactory=new MatchTypeFactory();
//        MatchType m= matchTypeFactory.getMatchType(match.getMatchType());
//        m.setMatchDetails(match);
//        return matchServices.startMatchService(match);
//    }
*/
    @GetMapping(value="/view")
    public Match viewMatch(@RequestParam String matchId) throws Exception {
        return matchServices.viewMatch(matchId);
    }

    @PostMapping(value="/start")
    public MatchResponse startMatch(@RequestBody MatchRequest matchRequest) {

        IMatchInterface matchInterface = matchFactory.getMatchService(matchRequest.getMatchType());
        return matchInterface.startMatch(matchRequest);

    }
}
