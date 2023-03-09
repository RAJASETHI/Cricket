package com.cricketGame.cricketgame.controller;

import com.cricketGame.cricketgame.model.Team;
import com.cricketGame.cricketgame.repository.TeamRepository;
import com.cricketGame.cricketgame.service.TeamServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamServices teamServices;
    @PostMapping("/createTeam")
    public String addTeam(@RequestBody @Valid Team team)
    {
        return teamServices.addTeamService(team);
    }
    @GetMapping("/allTeams")
    public List<Team> showAllTeams()
    {
        return teamServices.showAllTeamsService();
    }

    @PostMapping("/removeTeam")
    public String removeTeamByTeamId(@RequestParam String teamId)
    {
        return teamServices.removeTeamByTeamIdService(teamId);
    }
}
