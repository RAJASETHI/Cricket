package com.cricketGame.cricketgame.service;


import com.cricketGame.cricketgame.model.Player;
import com.cricketGame.cricketgame.repository.PlayerRepository;
import com.cricketGame.cricketgame.utils.GenerateRandom;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlayerServices {
    @Autowired
    private PlayerRepository playerRepository;

    public void addPlayer(@Valid Player player)
    {
        player.setPlayerId(UUID.randomUUID().toString());
        if(player.getBowlerRating()==-1)
        {
            player.setBowlerRating(GenerateRandom.randomRatingGenerator());
        }
        if(player.getBatsManRating()==-1)
        {
            player.setBatsManRating(GenerateRandom.randomRatingGenerator());
        }
        playerRepository.save(player);
    }
    public void updatePlayer(Player player)
    {
        playerRepository.save(player);
    }
    public Player viewPlayer(String playerId)
    {
        try{
            Player player = playerRepository.findById(playerId).get();
            return player;
        }
        catch (Exception e)
        {
            return null;
        }

    }
    public void updateWicketsTaken(String playerId, int wickets) {
        Player player = playerRepository.findById(playerId).get();
        player.setWicketsTaken(player.getWicketsTaken() + wickets);
        playerRepository.save(player);
    }

    public void updateRunsScored(String playerId, int runsScored) {
        Player player = playerRepository.findById(playerId).get();
        player.setRunsScored(player.getRunsScored()+runsScored);
        playerRepository.save(player);
    }

    public void updateConceded(String playerId, int runsConceded) {
        Player player = playerRepository.findById(playerId).get();
        player.setRunsConceded(player.getRunsConceded()+runsConceded);
        playerRepository.save(player);
    }
    public void updateBallsPlayed(String playerId,int ballsPlayed)
    {
        Player player = playerRepository.findById(playerId).get();
        player.setBallsPlayed(player.getBallsPlayed()+ballsPlayed);
        playerRepository.save(player);
    }

    public void updateBallsBowled(String playerId,int ballsBowled)
    {
        Player player = playerRepository.findById(playerId).get();
        player.setBallsBowled(player.getBallsBowled()+ballsBowled);
        playerRepository.save(player);
    }

    public void updateSix(String playerId,int six)
    {
        Player player = playerRepository.findById(playerId).get();
        player.setCountOfSix(player.getCountOfSix()+six);
        playerRepository.save(player);
    }
    public void updateFour(String playerId,int four)
    {
        Player player = playerRepository.findById(playerId).get();
        player.setCountOfFour(player.getCountOfFour()+four);
        playerRepository.save(player);
    }
}
