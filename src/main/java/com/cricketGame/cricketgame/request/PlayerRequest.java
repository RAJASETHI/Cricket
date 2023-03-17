package com.cricketGame.cricketgame.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequest {
    private String playerId;
    private String playerName;
    private int batsManRating=-1;
    private int runsScored;
    private int countOfSix;
    private int ballsPlayed;
    private int countOfFour;
    private int runsConceded;
    private int ballsBowled;
    private int bowlerRating=-1;
    private int wicketsTaken;
}
