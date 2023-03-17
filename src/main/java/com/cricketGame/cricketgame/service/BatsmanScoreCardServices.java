package com.cricketGame.cricketgame.service;

import com.cricketGame.cricketgame.model.BatsmanScoreCard;
import com.cricketGame.cricketgame.repository.BatsmanScoreCardRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Service
public class BatsmanScoreCardServices {
    @Autowired
    private BatsmanScoreCardRepository batsmanScoreCardRepository;

    public String addBatsmanScoreCardService(BatsmanScoreCard batsmanScoreCard)
    {
        batsmanScoreCardRepository.save(batsmanScoreCard);
        return "Added the Batsman Details Successfully!!";
    }
}