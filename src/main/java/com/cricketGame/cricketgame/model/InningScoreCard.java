package com.cricketGame.cricketgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="InningScoreCard")
public class InningScoreCard {
    @Id
    private String inningScoreCardId;
    private String matchId;
    private String teamId;
    private int teamRunsOfThisMatch;
    private int teamBallsPlayedOfThisMatch;

    private int teamWicketsGivenOfThisMatch;
    private ArrayList<String> BatsmenScoreCardId;
    private ArrayList<String>  BowlerScoreCardId;

    public InningScoreCard(String  teamId) {
        this.teamId = teamId;
        BatsmenScoreCardId = new ArrayList<>();
        BowlerScoreCardId = new ArrayList<>();
    }
    public void addBatsmenScoreCardId(String id)
    {
        this.BatsmenScoreCardId.add(id);
    }

    public void addBowlerScoreCardId(String id)
    {
        this.BowlerScoreCardId.add(id);
    }
}
