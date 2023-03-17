package com.cricketGame.cricketgame.service;

import com.cricketGame.cricketgame.model.InningScoreCard;
import com.cricketGame.cricketgame.repository.InningScoreCardRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InningScoreCardServices {
    @Autowired
    InningScoreCardRepository inningScoreCardRepository;

    public InningScoreCard viewInningScoreCard(String id)
    {
        return  inningScoreCardRepository.findById(id).get();
    }
}
