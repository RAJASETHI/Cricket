package com.cricketGame.cricketgame.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Player")
public class Player {
    @Id
    private String playerId;
    @NotNull
    private String playerName;
    @NotNull
    // Batsman Details
    private int batsManRating;
    private int runsScored;
    private int countOfSix;
    private int ballsPlayed;
    private int countOfFour;

    // Bowler Details
    private int runsConceded;
    private int ballsBowled;
    @NotNull
    private int bowlerRating;
    private int wicketsTaken;


    public Player(String playerName,int bowlerRating,int batsManRating)
    {
        this.playerName=playerName;
        this.bowlerRating=bowlerRating;
        this.batsManRating=batsManRating;
        this.wicketsTaken=this.runsConceded=this.runsScored=this.ballsBowled=this.ballsPlayed=this.countOfFour=this.countOfSix=0;

    }

}

