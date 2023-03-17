package com.cricketGame.cricketgame.service;


import com.cricketGame.cricketgame.Request.PlayerRequest;
import com.cricketGame.cricketgame.Response.PlayerResponse;
import com.cricketGame.cricketgame.model.Player;
import com.cricketGame.cricketgame.repository.PlayerRepository;
import com.cricketGame.cricketgame.utils.GenerateRandom;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
public class PlayerServices {
    @Autowired
    private PlayerRepository playerRepository;

    public PlayerResponse addPlayer(@Valid PlayerRequest player)
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
        return toPlayerResponse(playerRepository.save(toPlayer(player)));
    }

    public PlayerResponse toPlayerResponse(Player player) {
        return new PlayerResponse(player.getPlayerId(),player.getPlayerName(),player.getBatsManRating(),player.getRunsScored(),player.getCountOfSix(),player.getBallsPlayed(),player.getCountOfFour(),player.getRunsConceded(),player.getBallsBowled(),player.getBowlerRating(),player.getWicketsTaken());
    }
    public Player toPlayer(PlayerRequest playerRequest)
    {
        return new Player(playerRequest.getPlayerId(),playerRequest.getPlayerName(),playerRequest.getBatsManRating(),playerRequest.getRunsScored(),playerRequest.getCountOfSix(),playerRequest.getBallsPlayed(),playerRequest.getCountOfFour(),playerRequest.getRunsConceded(),playerRequest.getBallsBowled(),playerRequest.getBowlerRating(),playerRequest.getWicketsTaken());
    }

    public Player toPlayer(PlayerResponse playerResponse)
    {
        return new Player(playerResponse.getPlayerId(),playerResponse.getPlayerName(),playerResponse.getBatsManRating(),playerResponse.getRunsScored(),playerResponse.getCountOfSix(),playerResponse.getBallsPlayed(),playerResponse.getCountOfFour(),playerResponse.getRunsConceded(),playerResponse.getBallsBowled(),playerResponse.getBowlerRating(),playerResponse.getWicketsTaken());
    }
    public PlayerResponse updatePlayer(PlayerRequest playerRequest)
    {
        try
        {
            Player p=playerRepository.findById(playerRequest.getPlayerId()).get();
            return toPlayerResponse(playerRepository.save(toPlayer(playerRequest)));
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }
    public PlayerResponse updatePlayer(Player player)
    {
        try
        {
            Player p=playerRepository.findById(player.getPlayerId()).get();
            return toPlayerResponse(playerRepository.save(player));
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }
    public PlayerResponse viewPlayer(String playerId)
    {
        try
        {
            Optional<Player> player = playerRepository.findById(playerId);
            if(player.isPresent())
            {
                return toPlayerResponse(player.get());
            }
            throw new RuntimeException("No Player Exist with given Id. "+playerId);
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }

    }
    public List<PlayerResponse> showAllPlayers()
    {
        List<Player>players= playerRepository.findAll();
        List<PlayerResponse>fPlayers=new ArrayList<>();
        for(Player p:players)
        {
            fPlayers.add(toPlayerResponse(p));
        }
        return fPlayers;
    }
    public String removePlayer(String playerId)
    {
        try
        {
            Player p=playerRepository.findById(playerId).get();
            playerRepository.deleteById(playerId);
            return "Player Deleted Successfully.";
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }
//    public void updateWicketsTaken(String playerId, int wickets) {
//        Player player = playerRepository.findById(playerId).get();
//        player.setWicketsTaken(player.getWicketsTaken() + wickets);
//        playerRepository.save(player);
//    }
//
//    public void updateRunsScored(String playerId, int runsScored) {
//        Player player = playerRepository.findById(playerId).get();
//        player.setRunsScored(player.getRunsScored()+runsScored);
//        playerRepository.save(player);
//    }
//
//    public void updateConceded(String playerId, int runsConceded) {
//        Player player = playerRepository.findById(playerId).get();
//        player.setRunsConceded(player.getRunsConceded()+runsConceded);
//        playerRepository.save(player);
//    }
//    public void updateBallsPlayed(String playerId,int ballsPlayed)
//    {
//        Player player = playerRepository.findById(playerId).get();
//        player.setBallsPlayed(player.getBallsPlayed()+ballsPlayed);
//        playerRepository.save(player);
//    }
//
//    public void updateBallsBowled(String playerId,int ballsBowled)
//    {
//        Player player = playerRepository.findById(playerId).get();
//        player.setBallsBowled(player.getBallsBowled()+ballsBowled);
//        playerRepository.save(player);
//    }
//
//    public void updateSix(String playerId,int six)
//    {
//        Player player = playerRepository.findById(playerId).get();
//        player.setCountOfSix(player.getCountOfSix()+six);
//        playerRepository.save(player);
//    }
//    public void updateFour(String playerId,int four)
//    {
//        Player player = playerRepository.findById(playerId).get();
//        player.setCountOfFour(player.getCountOfFour()+four);
//        playerRepository.save(player);
//    }

}
