package aslmk.services.Impl;

import aslmk.dto.MatchDTO;
import aslmk.models.Player;
import aslmk.services.Impl.matchScoreCalculation.CurrentMatchScore;
import aslmk.services.Impl.matchScoreCalculation.MatchState;
import aslmk.services.MatchScoreCalculationService;

import java.util.UUID;

public class MatchScoreCalculationServiceImpl implements MatchScoreCalculationService {
    @Override
    public MatchState updateScore(int playerNumber, CurrentMatchScore currentMatchScore) {
        MatchState currentMatchState = currentMatchScore.pointWon(playerNumber);
        if (currentMatchState == MatchState.FIRST_PLAYER_WON) {
            return MatchState.FIRST_PLAYER_WON;
        } else if (currentMatchState == MatchState.SECOND_PLAYER_WON) {
            return MatchState.SECOND_PLAYER_WON;
        }
        return MatchState.ONGOING;
    }
    @Override
    public MatchDTO createMatchDTO(UUID match_uuid, CurrentMatchScore currentMatchScore, Player winner) {
        Player firstPlayer = currentMatchScore.getFirstPlayer();
        Player secondPlayer = currentMatchScore.getSecondPlayer();
        return new MatchDTO(match_uuid, firstPlayer, secondPlayer, winner);
    }
    @Override
    public Player determineWinner(Player player1, Player player2, MatchState currentMatchState) {
        if (currentMatchState == MatchState.FIRST_PLAYER_WON) {
            return player1;
        } else if (currentMatchState == MatchState.SECOND_PLAYER_WON) {
            return player2;
        }
        return null;
    }
}
