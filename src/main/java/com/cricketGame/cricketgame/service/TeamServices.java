package com.cricketGame.cricketgame.service;

import com.cricketGame.cricketgame.model.Team;
import com.cricketGame.cricketgame.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServices {
    @Autowired
    TeamRepository teamRepository;

    public String addTeamService(Team team)
    {
        try{
            Team fetchTeam=teamRepository.findById(team.getTeamId()).get();
        }
        catch(Exception e)
        {
            teamRepository.save(team);
            return "Team Added Successfully";
        }
        return "Team with this Id already exist!!";
    }
    public String removeTeamByTeamIdService(String teamId)
    {
        try
        {
            Team team=teamRepository.findById(teamId).get();
            teamRepository.delete(team);
        }
        catch (Exception e)
        {
            return "No Team found with This Id.";
        }
        return "Deleted Successfully";

    }
    public List<Team> showAllTeamsService()
    {
        return teamRepository.findAll();
    }
}
