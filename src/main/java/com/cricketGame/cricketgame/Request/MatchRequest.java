package com.cricketGame.cricketgame.Request;

import com.cricketGame.cricketgame.enums.TypeOfMatch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchRequest {
    private TypeOfMatch matchType;
    private String tossChosenByTeam1;
    private String team1Id;
    private String team2Id;
}
