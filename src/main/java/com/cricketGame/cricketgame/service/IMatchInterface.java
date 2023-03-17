package com.cricketGame.cricketgame.service;

import com.cricketGame.cricketgame.Request.MatchRequest;
import com.cricketGame.cricketgame.Response.MatchResponse;

public interface IMatchInterface {
    public MatchResponse startMatch(MatchRequest matchRequest);
}
