package com.cricketGame.cricketgame.repository;

import com.cricketGame.cricketgame.model.BatsmanScoreCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BatsmanScoreCardRepository extends MongoRepository<BatsmanScoreCard,String> {
        BatsmanScoreCard findByPlayerIdAndMatchIdAndInningScoreCardId(String playerId,String matchId,String inningScoreCardId);
}