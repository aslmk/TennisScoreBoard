package aslmk.services;

import aslmk.dto.MatchDTO;
import aslmk.models.Player;
import aslmk.services.Impl.matchScoreCalculation.CurrentMatchScore;
import aslmk.services.Impl.matchScoreCalculation.MatchState;

import java.util.UUID;

public interface MatchScoreCalculationService {
    MatchState updateScore(int playerNumber, CurrentMatchScore currentMatchScore);
    MatchDTO createMatchDTO(UUID match_uuid, CurrentMatchScore currentMatchScore, Player winner);
    Player determineWinner(Player player1, Player player2, MatchState currentMatchState);
}
