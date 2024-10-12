package aslmk.Services;

import aslmk.Models.MatchScore;
import aslmk.Models.Player;

import java.util.HashMap;
import java.util.UUID;

public interface OngoingMatchesService {
    HashMap<UUID, MatchScore> matchScore = new HashMap<>();
    void createNewMatch(Player player1, Player player2);
    MatchScore getMatchByUUID(UUID uuid);
    void removeMatch(UUID uuid);
    int getFirstPlayerId();
    int getSecondPlayerId();
}
