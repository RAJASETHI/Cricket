package com.cricketGame.cricketgame.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse {

    private String teamId;
    private String teamName;
    private ArrayList<String> playersListId;
}
