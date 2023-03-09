package com.cricketGame.cricketgame.controller;

import com.cricketGame.cricketgame.model.Match;
import com.cricketGame.cricketgame.repository.MatchRepository;
import com.cricketGame.cricketgame.service.MatchServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value="/match")
public class MatchController {
    @Autowired
    MatchServices matchServices;
    @Autowired
    MatchRepository matchRepository;
    @PostMapping(value="/start")
    public String goToCreateMatch(@RequestBody Match match)
    {
        //Team1,Team2,matchType
        match.setMatchId(UUID.randomUUID().toString());
        match.setTotalOver(6);
       String s=matchServices.startMatchService(match);
       matchRepository.save(match);
       return s;
    }

}
