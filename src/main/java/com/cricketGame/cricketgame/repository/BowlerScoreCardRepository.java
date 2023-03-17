package com.cricketGame.cricketgame.repository;

import com.cricketGame.cricketgame.model.BowlerScoreCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BowlerScoreCardRepository extends MongoRepository<BowlerScoreCard,String> {
       BowlerScoreCard findByPlayerIdAndMatchIdAndInningScoreCardId(String playerId,String matchId,String inningScoreCardId);
}