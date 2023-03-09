package com.cricketGame.cricketgame.repository;

import com.cricketGame.cricketgame.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team,String> {

}
