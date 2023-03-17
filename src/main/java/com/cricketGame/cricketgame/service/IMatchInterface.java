package com.cricketGame.cricketgame.service;

import com.cricketGame.cricketgame.request.MatchRequest;
import com.cricketGame.cricketgame.response.MatchResponse;

public interface IMatchInterface {
    public MatchResponse startMatch(MatchRequest matchRequest);
}
