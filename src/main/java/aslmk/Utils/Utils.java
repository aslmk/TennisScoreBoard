package aslmk.Utils;

import aslmk.Services.Impl.OngoingMatchesServiceImpl;

public class Utils {
    public static int getOpponentId(int playerId) {
        OngoingMatchesServiceImpl ongoingMatchesService = new OngoingMatchesServiceImpl();
        return playerId == ongoingMatchesService.getFirstPlayerId() ?
                ongoingMatchesService.getSecondPlayerId() : ongoingMatchesService.getFirstPlayerId();
    }
    public static int getPageNumber(String requestedPageNumber) {
        int pageNumber;
        try {
            pageNumber = Integer.parseInt(requestedPageNumber);
        } catch (NumberFormatException e) {
            pageNumber = 1;
        }
        return pageNumber <= 0 ? 1 : pageNumber;
    }
}
