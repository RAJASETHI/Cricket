package com.cricketGame.cricketgame.repository;

import com.cricketGame.cricketgame.model.BatsmanScoreCard;
import com.cricketGame.cricketgame.model.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface BatsmanScoreCardRepository extends MongoRepository<BatsmanScoreCard,String> {
        BatsmanScoreCard findByPlayerIdAndMatchIdAndInningScoreCardId(String playerId,String matchId,String inningScoreCardId);
}