package com.cricketGame.cricketgame.repository;

import com.cricketGame.cricketgame.model.InningScoreCard;
import com.cricketGame.cricketgame.model.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InningScoreCardRepository extends MongoRepository<InningScoreCard,String> {

}