package com.cricketGame.cricketgame.controller;

import com.cricketGame.cricketgame.Request.PlayerRequest;
import com.cricketGame.cricketgame.Response.PlayerResponse;
import com.cricketGame.cricketgame.model.Player;
import com.cricketGame.cricketgame.repository.PlayerRepository;
import com.cricketGame.cricketgame.repository.TeamRepository;
import com.cricketGame.cricketgame.service.PlayerServices;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerServices playerServices;

    @PostMapping("/")
    public ResponseEntity<PlayerResponse> addPlayer(@RequestBody @Valid PlayerRequest player) {
        return new ResponseEntity<>(playerServices.addPlayer(player),HttpStatus.OK);
    }

    @GetMapping(value = "/{playerId}")
    public ResponseEntity<PlayerResponse> viewPlayer(@PathVariable String playerId) {
        return new ResponseEntity<>(playerServices.viewPlayer(playerId), HttpStatus.OK);
    }
    @PutMapping(value="/")
    public ResponseEntity<PlayerResponse> updatePlayer(@RequestBody @Valid PlayerRequest player) {
        return new ResponseEntity<>(playerServices.updatePlayer(player),HttpStatus.OK);
    }

    @DeleteMapping(value="/{playerId}")
    public ResponseEntity<String> removePlayer(@PathVariable String playerId)
    {
        return new ResponseEntity<>(playerServices.removePlayer(playerId),HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<PlayerResponse>> showAllPlayers() {
        return new ResponseEntity<>(playerServices.showAllPlayers(),HttpStatus.OK);
    }

}
