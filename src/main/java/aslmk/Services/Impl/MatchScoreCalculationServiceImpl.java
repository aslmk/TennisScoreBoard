package aslmk.Services.Impl;

import aslmk.DAO.PlayersDAO;
import aslmk.Exceptions.InvalidParametersException;
import aslmk.Exceptions.MatchNotFoundException;
import aslmk.Models.Match;
import aslmk.Models.MatchScore;
import aslmk.Models.Player;
import aslmk.Services.MatchScoreCalculationService;
import aslmk.Utils.Utils;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;
import java.util.UUID;

public class MatchScoreCalculationServiceImpl implements MatchScoreCalculationService {
    OngoingMatchesServiceImpl ongoingMatchesService = new OngoingMatchesServiceImpl();
    PlayersDAO playersDAO = new PlayersDAO();

    @Override
    public MatchScore updatePlayerScore(UUID match_uuid, int playerId) throws MatchNotFoundException {
        MatchScore currentMatch = ongoingMatchesService.getMatchByUUID(match_uuid);

        if (currentMatch == null) {
            throw new MatchNotFoundException();
        }

        int opponentId = Utils.getOpponentId(playerId, currentMatch);


        if (isDeuce(currentMatch, playerId, opponentId)) {
            handleDeuce(currentMatch, playerId);
        }
        if (isGameWinner(currentMatch, playerId, opponentId)) {
            currentMatch.incrementGamesOfPlayer(playerId);
            currentMatch.resetPlayersPoints();
        }
        if (isSetWinner(currentMatch, playerId, opponentId)) {
            currentMatch.incrementSetsOfPlayer(playerId);
            currentMatch.resetPlayerGames();
        } else if (isTieBreak(currentMatch, playerId, opponentId)) {
            if (isTieBreakWinner(currentMatch, playerId, opponentId)) {
                currentMatch.incrementSetsOfPlayer(playerId);
                currentMatch.resetPlayerGames();
            }
            currentMatch.incrementTieBreakPointsOfPlayer(playerId);
        }

        addPointsToPlayer(playerId, currentMatch);


        return currentMatch;
    }

    private void addPointsToPlayer(int playerId, MatchScore match) {
        int playerPoints = match.getPointsOfPlayer(playerId);

        if (playerPoints < 30) {
            match.incrementPointsOfPlayer(playerId, 15);
        } else {
            match.incrementPointsOfPlayer(playerId, 10);
        }
    }
    public boolean isDeuce(MatchScore match, int playerId, int opponentId) {
        return match.getPointsOfPlayer(playerId) == 40 && match.getPointsOfPlayer(opponentId) == 40;

    }
    private void handleDeuce(MatchScore match, int playerId) {
        int advantage = match.getAdvantageOfPlayer();
        if (advantage == -1) {
            match.setAdvantageOfPlayer(playerId);
        } else if (advantage == playerId) {
            match.incrementGamesOfPlayer(playerId);
            match.resetPointsOfPlayer(playerId);
            match.resetAdvantage();
        } else {
            match.resetAdvantage();
        }
    }
    public boolean isGameWinner(MatchScore match, int playerId, int opponentId) {
        int pointsDiff = match.getPointsOfPlayer(playerId) - match.getPointsOfPlayer(opponentId);
        return match.getPointsOfPlayer(playerId) >= 40 && pointsDiff >= 2;
    }
    public boolean isSetWinner(MatchScore match, int playerId, int opponentId) {
        int gamesCountOfPlayer = match.getGamesOfPlayer(playerId);
        int gamesCountOfOpponent = match.getGamesOfPlayer(opponentId);
        int gamesCountDiff = gamesCountOfPlayer - gamesCountOfOpponent;
        return gamesCountOfPlayer > 6 && gamesCountDiff >= 2;
    }
    public boolean isTieBreak(MatchScore match, int playerId, int opponentId) {
        int gamesCountOfPlayer = match.getGamesOfPlayer(playerId);
        int gamesCountOfOpponent = match.getGamesOfPlayer(opponentId);
        return gamesCountOfPlayer == 6 && gamesCountOfOpponent == 6;
    }
    public boolean isTieBreakWinner(MatchScore match, int playerId, int opponentId) {
        int tieBreakPointsCountOfPlayer = match.getTieBreakPoints(playerId);
        int tieBreakPointsCountOfOpponent = match.getTieBreakPoints(opponentId);
        int tieBreakPointsDiff = tieBreakPointsCountOfPlayer - tieBreakPointsCountOfOpponent;
        return tieBreakPointsCountOfPlayer >= 7 && tieBreakPointsDiff >= 2;
    }
    public boolean isMatchWinner(MatchScore match, int playerId) {
        int setsCountOfPlayer = match.getSetsOfPlayer(playerId);
        return setsCountOfPlayer == 2;
    }

    public Match FinishedMatch(Player winnerPlayer, MatchScore currentMatchScore) {
        Player firstPlayer = playersDAO.getPlayerById(currentMatchScore.getFirstPlayerId());
        Player secondPlayer = playersDAO.getPlayerById(currentMatchScore.getSecondPlayerId());

        if (firstPlayer == null || secondPlayer == null || winnerPlayer == null) {
            throw new InvalidParametersException("Invalid parameters!");
        }
        Match match = new Match();
        match.setFirstPlayer(firstPlayer);
        match.setSecondPlayer(secondPlayer);
        match.setWinner(winnerPlayer);

        return match;
    }
}
