package com.cricketGame.cricketgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "BowlerScoreCard")

@CompoundIndex(name = "myindex2", def = "{'playerId': 1, 'matchId': 1,'inningScoreCardId': 1}", unique = true)
public class BowlerScoreCard {
    private String playerId;

    private String matchId;
    private String inningScoreCardId;
    private int runsConceded;
    private int ballsBowled;
    private int wicketsTaken;


    public BowlerScoreCard(String playerId,String matchId,String inningScoreCardId)
    {
        this.playerId=playerId;
        this.matchId=matchId;
        this.inningScoreCardId=inningScoreCardId;
    }
}
