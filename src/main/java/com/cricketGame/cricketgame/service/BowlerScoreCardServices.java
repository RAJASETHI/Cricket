package com.cricketGame.cricketgame.service;

import com.cricketGame.cricketgame.model.BatsmanScoreCard;
import com.cricketGame.cricketgame.model.BowlerScoreCard;
import com.cricketGame.cricketgame.repository.BowlerScoreCardRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Service
public class BowlerScoreCardServices {
    @Autowired
    private BowlerScoreCardRepository bowlerScoreCardRepository;

    public String addBowlerScorecardService(BowlerScoreCard bowlerScoreCard)
    {
        bowlerScoreCardRepository.save(bowlerScoreCard);
        return "Added the Batsman Details Successfully!!";
//        try
//        {
//            BowlerScoreCard fetchIfAlreadyExist=bowlerScoreCardRepository.findByPlayerIdAndMatchIdAndInningScoreCardId(bowlerScoreCard.getPlayerId(),bowlerScoreCard.getMatchId(),bowlerScoreCard.getInningScoreCardId());
//            return "Batsman Already played the Match";
//        }
//        catch(Exception e)
//        {
//            return "Added the Batsman Details Successfully!!";
//        }
    }
}
