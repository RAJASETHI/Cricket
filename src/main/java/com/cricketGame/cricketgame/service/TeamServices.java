package com.cricketGame.cricketgame.service;

import com.cricketGame.cricketgame.request.TeamRequest;
import com.cricketGame.cricketgame.response.PlayerResponse;
import com.cricketGame.cricketgame.response.TeamResponse;
import com.cricketGame.cricketgame.model.Player;
import com.cricketGame.cricketgame.model.Team;
import com.cricketGame.cricketgame.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServices {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerServices playerServices;

    public TeamResponse addTeamService(TeamRequest teamRequest) {
        try
        {
            Optional<Team> teamExistInDatabase = teamRepository.findById(teamRequest.getTeamId());
            if (teamExistInDatabase.isPresent()) {
                throw new RuntimeException("Team with this Id already exist!!");
            }
            else
            {
                return toTeamResponse(teamRepository.save(toTeam(teamRequest)));
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }


    public TeamResponse updateTeamService(TeamRequest teamRequest) {
        try
        {
            Optional<Team> teamExistInDatabase = teamRepository.findById(teamRequest.getTeamId());
            if (teamExistInDatabase.isPresent()) {
                return toTeamResponse(teamRepository.save(toTeam(teamRequest)));
            }
            else
            {
                throw new RuntimeException("Team with this Id does not exist!!");
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }
    private TeamResponse toTeamResponse(Team team) {
        return new TeamResponse(team.getTeamId(),team.getTeamName(),team.getPlayersListId());
    }

    public Team toTeam(TeamRequest teamRequest)
    {
        return new Team(teamRequest.getTeamId(),teamRequest.getTeamName(),teamRequest.getPlayersListId());
    }
    public String removeTeamByTeamIdService(String teamId) {
        try{

            Optional<Team> teamExistInDatabase = teamRepository.findById(teamId);
            if (teamExistInDatabase.isPresent()) {
                teamRepository.delete(teamExistInDatabase.get());
                return "Deleted The Team Successfully.";
            }
            throw new RuntimeException("No Team found with This Id.");
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<TeamResponse> showAllTeamsService() {
        try
        {
            List<Team>teams= teamRepository.findAll();
            List<TeamResponse>teamsResponses=new ArrayList<>();
            for(Team team:teams)
            {
                teamsResponses.add(toTeamResponse(team));
            }
            return teamsResponses;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public ArrayList<PlayerResponse> showPlayersOfTeam(@RequestParam String teamId)  {
        try{
            Optional<Team> team=teamRepository.findById(teamId);
            if(team.isPresent())
            {
                ArrayList<PlayerResponse>playersOfTeam=new ArrayList<>();
                for (String id:team.get().getPlayersListId())
                {
                    playersOfTeam.add(playerServices.viewPlayer(id));
                }
                return playersOfTeam;
            }
            else
            {
                throw new RuntimeException("Team Does not exist with the given Id.");
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }

    }

    public ArrayList<Player> showPlayersOfaTeam(@RequestParam String teamId)  {
        try{
            Optional<Team> team=teamRepository.findById(teamId);
            if(team.isPresent())
            {
                ArrayList<Player>playersOfTeam=new ArrayList<>();
                for (String id:team.get().getPlayersListId())
                {
                    playersOfTeam.add(playerServices.toPlayer(playerServices.viewPlayer(id)));
                }
                return playersOfTeam;
            }
            else
            {
                throw new RuntimeException("Team Does not exist with the given Id.");
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }

    }

}
