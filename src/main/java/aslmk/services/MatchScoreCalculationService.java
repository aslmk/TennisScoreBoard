package aslmk.services;

import aslmk.models.MatchScore;

import java.util.UUID;

public interface MatchScoreCalculationService {
    MatchScore updatePlayerScore(UUID match_uuid, int playerId);
}
