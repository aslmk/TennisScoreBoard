package aslmk.Utils;

import aslmk.Services.Impl.OngoingMatchesServiceImpl;

public class Utils {

    public static int getOpponentId(int playerId) {
        OngoingMatchesServiceImpl ongoingMatchesService = new OngoingMatchesServiceImpl();
        return playerId == ongoingMatchesService.getFirstPlayerId() ?
                ongoingMatchesService.getSecondPlayerId() : ongoingMatchesService.getFirstPlayerId();
    }
}
