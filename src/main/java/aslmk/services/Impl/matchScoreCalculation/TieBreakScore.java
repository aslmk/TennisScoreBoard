package aslmk.services.Impl.matchScoreCalculation;

public class TieBreakScore extends Score<Integer> {

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public MatchState pointWon(int playerNumber) {
        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);

        int scoreDiff = getPlayerScore(playerNumber) - getOpponentScore(playerNumber);
        if (getPlayerScore(playerNumber) >= 7 && scoreDiff >= 2) {
            return playerNumber == 0 ? MatchState.FIRST_PLAYER_WON : MatchState.SECOND_PLAYER_WON;
        }
        return MatchState.ONGOING;
    }

}
