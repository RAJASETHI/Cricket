package com.cricketGame.cricketgame.model;

import com.cricketGame.cricketgame.Enum.TypeOfMatch;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

// Make interface  and create different
@Data
@Document(collection = "Match")
public class Match {
    @Id
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
    private ArrayList<String>team1InningScoreCard;
    private ArrayList<String>team2InningScoreCard;

    public Match(String team1Id,String team2Id,String tossChosenByTeam1)
    {
        this.team1Id=team1Id;
        this.team2Id=team2Id;
        this.tossChosenByTeam1=tossChosenByTeam1;
        team1InningScoreCard=new ArrayList<>();
        team2InningScoreCard=new ArrayList<>();
    }
    public void addTeam1InningScoreCard(String id)
    {
        this.team1InningScoreCard.add(id);
    }

    public void addTeam2InningScoreCard(String id)
    {
        this.team2InningScoreCard.add(id);
    }

}
