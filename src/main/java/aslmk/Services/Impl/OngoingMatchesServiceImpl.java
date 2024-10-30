package aslmk.Services.Impl;

import aslmk.Exceptions.MatchNotFoundException;
import aslmk.Models.MatchScore;
import aslmk.Models.Player;
import aslmk.Services.OngoingMatchesService;

import java.util.UUID;

public class OngoingMatchesServiceImpl implements OngoingMatchesService {
    @Override
    public UUID createNewMatch(Player firstPlayer, Player secondPlayer) {
        MatchScore newMatch = new MatchScore(firstPlayer, secondPlayer);

        UUID match_uuid = UUID.randomUUID();
        matchScore.put(match_uuid, newMatch);
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
