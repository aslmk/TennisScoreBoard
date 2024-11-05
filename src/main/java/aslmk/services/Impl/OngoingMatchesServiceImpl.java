package aslmk.services.Impl;

import aslmk.models.Player;
import aslmk.services.OngoingMatchesService;
import aslmk.services.Impl.matchScoreCalculation.CurrentMatchScore;

import java.util.UUID;

public class OngoingMatchesServiceImpl implements OngoingMatchesService {
    @Override
    public CurrentMatchScore createNewMatch(Player firstPlayer, Player secondPlayer) {
        UUID match_uuid = UUID.randomUUID();

        CurrentMatchScore newMatch = new CurrentMatchScore(match_uuid, firstPlayer, secondPlayer);
        matchScore.put(match_uuid, newMatch);
        return newMatch;
    }
    @Override
    public CurrentMatchScore getMatchByUUID(UUID uuid) {
        return matchScore.get(uuid);
    }
    @Override
    public void removeMatch(UUID uuid) {
        matchScore.remove(uuid);
    }

}
