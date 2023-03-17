package com.cricketGame.cricketgame.controller;

import com.cricketGame.cricketgame.Request.TeamRequest;
import com.cricketGame.cricketgame.Response.PlayerResponse;
import com.cricketGame.cricketgame.Response.TeamResponse;
import com.cricketGame.cricketgame.service.TeamServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamServices teamServices;
    @GetMapping("/all")
    public ResponseEntity<List<TeamResponse>> showAllTeams()
    {
        return new ResponseEntity<>( teamServices.showAllTeamsService(),HttpStatus.OK);
    }


    @GetMapping(value="/{teamId}")
    public ResponseEntity<ArrayList<PlayerResponse>> showPlayersOfTeam(@PathVariable String teamId) throws Exception {
        return new ResponseEntity<>(teamServices.showPlayersOfTeam(teamId),HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<TeamResponse> addTeam(@RequestBody @Valid TeamRequest teamRequest)
    {
        return new ResponseEntity<>(teamServices.addTeamService(teamRequest), HttpStatus.OK);
    }
    @PutMapping("/")
    public ResponseEntity<TeamResponse> updateTeam(@RequestBody @Valid TeamRequest teamRequest)
    {
        return new ResponseEntity<>(teamServices.updateTeamService(teamRequest),HttpStatus.OK);
    }
    @DeleteMapping("/{teamId}")
    public ResponseEntity<String> removeTeamByTeamId(@PathVariable String teamId)
    {
        return new ResponseEntity<>(teamServices.removeTeamByTeamIdService(teamId),HttpStatus.OK);
    }
}
