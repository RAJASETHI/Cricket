package com.cricketGame.cricketgame.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequest {

    private String teamId;
    private String teamName;
    private ArrayList<String> playersListId;
}
