package aslmk.services;

import aslmk.models.MatchScore;
import aslmk.models.Player;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public interface OngoingMatchesService {
    ConcurrentHashMap<UUID, MatchScore> matchScore = new ConcurrentHashMap<>();
    UUID createNewMatch(Player player1, Player player2);
    MatchScore getMatchByUUID(UUID uuid);
    void removeMatch(UUID uuid);
}
