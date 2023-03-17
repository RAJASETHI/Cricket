package com.cricketGame.cricketgame.controller;

import com.cricketGame.cricketgame.request.MatchRequest;
import com.cricketGame.cricketgame.response.MatchResponse;
import com.cricketGame.cricketgame.factory.MatchFactory;
import com.cricketGame.cricketgame.model.Match;
import com.cricketGame.cricketgame.service.IMatchInterface;
import com.cricketGame.cricketgame.service.MatchServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/match")
public class MatchController {
    @Autowired
    private MatchServices matchServices;

    @Autowired
    private MatchFactory matchFactory;
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
