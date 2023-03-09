package com.cricketGame.cricketgame.controller;

import com.cricketGame.cricketgame.model.Player;
import com.cricketGame.cricketgame.repository.PlayerRepository;
import com.cricketGame.cricketgame.repository.TeamRepository;
import com.cricketGame.cricketgame.service.PlayerServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerServices playerServices;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @PostMapping("/create")
    public String createPlayer(@RequestBody @Valid Player player)
    {
        playerServices.addPlayer(player);
        return "";
    }
    @GetMapping(value="/createPlayer")
    public String showCreatePlayer()
    {
        return "";
    }
    @GetMapping(value="/allPlayers")
    public List<Player> showAllPlayers()
    {
        return playerRepository.findAll();
    }

    @GetMapping(value="/allPlayersOfTeam")
    public ArrayList<Optional<Player>> showPlayersOfTeam(@RequestParam String teamId)
    {
        //add validation
//        if(teamRepository.findBy)
        ArrayList<Optional<Player>> players=new ArrayList<>();
        ArrayList<String>playersId=new ArrayList<>();

        playersId=teamRepository.findById(teamId).get().getPlayersListId();

        for(String id:playersId)
        {
            players.add(playerRepository.findById(id));
        }
        return players;
    }

}
