package aslmk.Services;

import aslmk.Models.MatchScore;

import java.util.UUID;

public interface MatchScoreCalculationService {
    MatchScore updatePlayerScore(UUID match_uuid, int playerId);
}
