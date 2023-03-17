package com.cricketGame.cricketgame.response;

import com.cricketGame.cricketgame.enums.TypeOfMatch;
import com.cricketGame.cricketgame.model.InningScoreCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchResponse {

    private String matchId;
    // add enum of match type and abstract design pattern.
    private TypeOfMatch matchType;
    private int totalOver;
    // Should Store Id of team or teh Object Directly again?
    private String tossChosenByTeam1;
    private String team1Id;
    private String team2Id;
    // Should Store Id of Inning or The Object Only?
//    private String team1InningScoreCard;// Create array for multiple innings.
    private String Winner;
    private ArrayList<InningScoreCard> team1InningScoreCard;
    private ArrayList<InningScoreCard>team2InningScoreCard;
}
