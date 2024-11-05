package aslmk.services;

import aslmk.models.Player;
import aslmk.services.Impl.matchScoreCalculation.CurrentMatchScore;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public interface OngoingMatchesService {
    ConcurrentHashMap<UUID, CurrentMatchScore> matchScore = new ConcurrentHashMap<>();
    CurrentMatchScore createNewMatch(Player player1, Player player2);
    CurrentMatchScore getMatchByUUID(UUID uuid);
    void removeMatch(UUID uuid);
}
