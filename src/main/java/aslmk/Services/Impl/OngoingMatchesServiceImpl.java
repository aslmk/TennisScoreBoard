package aslmk.Services.Impl;

import aslmk.Models.MatchScore;
import aslmk.Models.Player;
import aslmk.Services.OngoingMatchesService;

import java.util.UUID;

public class OngoingMatchesServiceImpl implements OngoingMatchesService {
    private static UUID match_uuid;

    @Override
    public void createNewMatch(Player firstPlayer, Player secondPlayer) {
        int firstPlayerId = firstPlayer.getId();
        int secondPlayerId = secondPlayer.getId();
        MatchScore newMatch = new MatchScore(firstPlayerId, secondPlayerId);

        match_uuid = UUID.randomUUID();
        matchScore.put(match_uuid, newMatch);
    }

    public UUID getUuidOfMatch() {
        return match_uuid;
    }
    @Override
    public MatchScore getMatchByUUID(UUID uuid) {
        return matchScore.get(uuid);
    }
    @Override
    public void removeMatch(UUID uuid) {
        matchScore.remove(uuid);
    }

}
