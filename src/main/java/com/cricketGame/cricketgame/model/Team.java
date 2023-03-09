package com.cricketGame.cricketgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="Team")
public class Team {
    @Id
    private String teamId;
    private String teamName;
    private ArrayList<String> playersListId;


    Team(String teamName) {
        this.teamName = teamName;
        playersListId = new ArrayList<>();
    }


}