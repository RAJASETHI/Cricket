package com.cricketGame.cricketgame.utils;

import com.cricketGame.cricketgame.model.Player;

import java.util.Comparator;

public class SortByBowlerRatings implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return o2.getBowlerRating()-o1.getBowlerRating();
    }

}
