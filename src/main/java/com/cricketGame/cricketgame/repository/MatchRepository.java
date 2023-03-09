package com.cricketGame.cricketgame.repository;

import com.cricketGame.cricketgame.model.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<Match,String> {

}