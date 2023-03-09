package com.cricketGame.cricketgame.repository;

import com.cricketGame.cricketgame.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player,String> {
//    public List<Player> findByTeamId(String teamId);
}