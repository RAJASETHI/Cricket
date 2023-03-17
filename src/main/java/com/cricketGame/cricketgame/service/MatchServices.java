package com.cricketGame.cricketgame.service;

import com.cricketGame.cricketgame.request.MatchRequest;
import com.cricketGame.cricketgame.response.MatchResponse;
import com.cricketGame.cricketgame.model.*;
import com.cricketGame.cricketgame.repository.InningScoreCardRepository;
import com.cricketGame.cricketgame.repository.MatchRepository;
import com.cricketGame.cricketgame.utils.GenerateRandom;
import com.cricketGame.cricketgame.utils.SortByBatsmanRatings;
import com.cricketGame.cricketgame.utils.SortByBowlerRatings;
import com.cricketGame.cricketgame.utils.Toss;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Service
public class MatchServices {

    @Autowired
    private PlayerServices playerServices;

    @Autowired
    private InningScoreCardRepository inningScoreCardRepository;

    @Autowired
    private InningScoreCardServices inningScoreCardServices;

    @Autowired
    private BatsmanScoreCardServices batsmanScoreCardServices;

    @Autowired
    private BowlerScoreCardServices bowlerScoreCardServices;

    @Autowired
    private TeamServices teamServices;

    @Autowired
    private MatchRepository matchRepository;
    public String getResultOfMatch(Match match, int targetRuns, int finalScore) {
        String res;
        if (finalScore > targetRuns) {
            match.setWinner(match.getTeam2Id());
            res = match.getTeam2Id() + " won the match.";
        } else if (targetRuns > finalScore) {

            match.setWinner(match.getTeam1Id());
            res = match.getTeam1Id() + " won the match.";
        } else {
            match.setWinner("Drawn");
            res = "match is drawn.";
        }

        return res;
    }
    public void doTossService(Match match)
    {
        if (!Toss.toss(match.getTossChosenByTeam1())) {
            String team1 = match.getTeam1Id();
            match.setTeam1Id(match.getTeam2Id());
            match.setTeam2Id(team1);
        }
    }
    public MatchResponse toMatchResponse(Match match)
    {
        ArrayList<InningScoreCard>inningScoreCards1=new ArrayList<>();
        ArrayList<InningScoreCard>inningScoreCards2=new ArrayList<>();
        for(String id:match.getTeam1InningScoreCard())
        {
            inningScoreCards1.add(inningScoreCardServices.viewInningScoreCard(id));
        }
        for(String id:match.getTeam2InningScoreCard())
        {
            inningScoreCards2.add(inningScoreCardServices.viewInningScoreCard(id));
        }
        return new MatchResponse(match.getMatchId(),match.getMatchType(),match.getTotalOver(),match.getTossChosenByTeam1(),match.getTeam1Id(),match.getTeam2Id(),match.getWinner(),inningScoreCards1,inningScoreCards2);
    }
    public Match toMatch(MatchRequest matchRequest)
    {
        return new Match(matchRequest.getMatchType(),matchRequest.getTossChosenByTeam1(),matchRequest.getTeam1Id(),matchRequest.getTeam2Id());
    }
    public Match startMatchService(Match match) throws Exception {
        Team battingTeam, bowlingTeam;
        ArrayList<Player> battingTeamPlayerList, bowlerTeamPlayerList;
        battingTeamPlayerList = teamServices.showPlayersOfaTeam(match.getTeam1Id());
        bowlerTeamPlayerList = teamServices.showPlayersOfaTeam(match.getTeam2Id());
        int targetRuns = inning(match, battingTeamPlayerList, bowlerTeamPlayerList, Integer.MAX_VALUE);

        int finalScore = inning(match, bowlerTeamPlayerList, battingTeamPlayerList, targetRuns);

        String res=getResultOfMatch(match, targetRuns, finalScore);
        matchRepository.save(match);
        return match;
    }

    public int inning(Match match, ArrayList<Player> battingTeamPlayerList, ArrayList<Player> bowlerTeamPlayerList, int targetRuns) {
        //Sorting the batting and the Bowling team Players
        battingTeamPlayerList.sort(new SortByBatsmanRatings());
        bowlerTeamPlayerList.sort(new SortByBowlerRatings());

        //Creating the Inning of the Batting Team and setting up the inningId,matchId,teamId
        InningScoreCard inningScoreCard = new InningScoreCard(match.getTeam1Id());
        inningScoreCard.setInningScoreCardId(UUID.randomUUID().toString());
        inningScoreCard.setMatchId(match.getMatchId());


        //Adding the Inning Id in the Match Object
        if (targetRuns == Integer.MAX_VALUE) {
            match.addTeam1InningScoreCard(inningScoreCard.getInningScoreCardId());
        } else {
            inningScoreCard.setTeamId(match.getTeam2Id());
            match.addTeam2InningScoreCard(inningScoreCard.getInningScoreCardId());
        }
        int batsmanIdx = 0, bowlingIdx = 0;
        boolean isMatchOver = false;

        // Creating the Batsman ScoreCard
        BatsmanScoreCard batsmanScoreCard = new BatsmanScoreCard(battingTeamPlayerList.get(batsmanIdx).getPlayerId(), match.getMatchId(), inningScoreCard.getInningScoreCardId());

        // Creating the Bowling ScoreCard
        BowlerScoreCard bowlerScoreCard = new BowlerScoreCard(bowlerTeamPlayerList.get(bowlingIdx).getPlayerId(), match.getMatchId(), inningScoreCard.getInningScoreCardId());
        Player battingPlayer = null, bowlerPlayer = null;

        for (int i = 0; i < match.getTotalOver(); i++) {
            for (int j = 0; j < 6 && inningScoreCard.getTeamRunsOfThisMatch() <= targetRuns && batsmanIdx < battingTeamPlayerList.size(); j++) {
                battingPlayer = battingTeamPlayerList.get(batsmanIdx);
                bowlerPlayer = bowlerTeamPlayerList.get(bowlingIdx);

                //Outcome of the Ball Generated
                Object ballOutcome = GenerateRandom.randomBallGenerator(battingPlayer.getBatsManRating(), bowlerPlayer.getBowlerRating());

                //Balls Played and Bowled in this match
                bowlerPlayer.setBallsBowled(bowlerPlayer.getBallsBowled() + 1);
                battingPlayer.setBallsPlayed(battingPlayer.getBallsPlayed() + 1);
                inningScoreCard.setTeamBallsPlayedOfThisMatch(inningScoreCard.getTeamBallsPlayedOfThisMatch() + 1);
                batsmanScoreCard.setBallsPlayed(batsmanScoreCard.getBallsPlayed() + 1);
                bowlerScoreCard.setBallsBowled(bowlerScoreCard.getBallsBowled() + 1);


                //Outcome Of The Ball Logic
                if (ballOutcome.equals('W')) {

                    batsmanScoreCardServices.addBatsmanScoreCardService(batsmanScoreCard);
                    playerServices.updatePlayer(battingPlayer);
                    bowlerScoreCard.setWicketsTaken(bowlerScoreCard.getWicketsTaken() + 1);
                    bowlerPlayer.setWicketsTaken(bowlerPlayer.getWicketsTaken() + 1);
                    inningScoreCard.setTeamWicketsGivenOfThisMatch(inningScoreCard.getTeamWicketsGivenOfThisMatch() + 1);
                    inningScoreCard.addBatsmenScoreCardId(battingPlayer.getPlayerId());
                    batsmanIdx++;
                    if (batsmanIdx < battingTeamPlayerList.size()) {
                        batsmanScoreCard = new BatsmanScoreCard(battingTeamPlayerList.get(batsmanIdx).getPlayerId(), match.getMatchId(), inningScoreCard.getInningScoreCardId());
                    } else {
                        isMatchOver = true;
                    }
                } else {
                    //Adding the runs in the inning report
                    inningScoreCard.setTeamRunsOfThisMatch(inningScoreCard.getTeamRunsOfThisMatch() + (int) ballOutcome);

                    //Adding the Batsman ScoreCard
                    batsmanScoreCard.setRunsScored(batsmanScoreCard.getRunsScored() + (int) ballOutcome);
                    battingPlayer.setRunsScored(battingPlayer.getRunsScored() + (int) ballOutcome);
                    if ((int) ballOutcome == 4) {
                        battingPlayer.setCountOfFour(battingPlayer.getCountOfFour() + 1);
                        batsmanScoreCard.setCountOfFour(batsmanScoreCard.getCountOfFour() + 1);
                    } else if ((int) ballOutcome == 6) {
                        battingPlayer.setCountOfSix(battingPlayer.getCountOfSix() + 1);
                        batsmanScoreCard.setCountOfSix(batsmanScoreCard.getCountOfSix() + 1);
                    }
                    bowlerScoreCard.setRunsConceded(bowlerScoreCard.getRunsConceded() + (int) ballOutcome);
                    bowlerPlayer.setRunsConceded(bowlerPlayer.getRunsConceded() + (int) ballOutcome);
                }
                if (batsmanIdx >= battingTeamPlayerList.size() || (i == match.getTotalOver() - 1 && j == 5) || inningScoreCard.getTeamRunsOfThisMatch() > targetRuns) {
                    if (batsmanIdx != battingTeamPlayerList.size()) {

                        playerServices.updatePlayer(battingPlayer);
                        batsmanScoreCardServices.addBatsmanScoreCardService(batsmanScoreCard);
                        inningScoreCard.addBatsmenScoreCardId(batsmanScoreCard.getPlayerId());
                    }
                    bowlerScoreCardServices.addBowlerScorecardService(bowlerScoreCard);
                    playerServices.updatePlayer(bowlerPlayer);
                    inningScoreCard.addBowlerScoreCardId(bowlerTeamPlayerList.get(bowlingIdx).getPlayerId());
                    inningScoreCardRepository.save(inningScoreCard);
                    isMatchOver = true;
                }

            }

            if (i > 0 && i % 4 == 0 && !isMatchOver) {
                bowlerScoreCardServices.addBowlerScorecardService(bowlerScoreCard);
                playerServices.updatePlayer(bowlerPlayer);
                inningScoreCard.addBowlerScoreCardId(bowlerTeamPlayerList.get(bowlingIdx).getPlayerId());
                bowlingIdx++;
                bowlerScoreCard = new BowlerScoreCard(bowlerTeamPlayerList.get(bowlingIdx).getPlayerId(), match.getMatchId(), inningScoreCard.getInningScoreCardId());


            }
            if (isMatchOver) break;
        }
        return inningScoreCard.getTeamRunsOfThisMatch();
    }

    public Match viewMatch(String matchId) throws Exception {
        Optional<Match> match=matchRepository.findById(matchId);
        if(match.isPresent())
        {
            return match.get();
        }
        throw new Exception("No Match found with this Id.");
    }
}
