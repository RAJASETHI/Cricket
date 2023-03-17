package com.cricketGame.cricketgame.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponse {

    private String playerId;
    private String playerName;
    private int batsManRating;
    private int runsScored;
    private int countOfSix;
    private int ballsPlayed;
    private int countOfFour;
    private int runsConceded;
    private int ballsBowled;
    private int bowlerRating;
    private int wicketsTaken;
}
