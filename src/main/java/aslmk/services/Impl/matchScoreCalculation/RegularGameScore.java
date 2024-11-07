package aslmk.services.Impl.matchScoreCalculation;

public class RegularGameScore extends Score<RegularGamePlayerPoints> {
    @Override
    public MatchState pointWon(int playerNumber) {
        RegularGamePlayerPoints playerScore = getPlayerScore(playerNumber);

        if (playerScore.ordinal() <= RegularGamePlayerPoints.THIRTY.ordinal()) {
            playerScores.set(playerNumber, playerScore.next());
        } else if (playerScore == RegularGamePlayerPoints.FORTY) {
            RegularGamePlayerPoints opponentScore = getOpponentScore(playerNumber);
            if (opponentScore == RegularGamePlayerPoints.ADVANTAGE) {
                setOpponentScore(playerNumber, RegularGamePlayerPoints.FORTY);
            } else if (opponentScore == RegularGamePlayerPoints.FORTY) {
                setPlayerScore(playerNumber, RegularGamePlayerPoints.ADVANTAGE);
            } else {
                return playerNumber == 0 ? MatchState.FIRST_PLAYER_WON : MatchState.SECOND_PLAYER_WON;
            }
        } else if (playerScore == RegularGamePlayerPoints.ADVANTAGE) {
            return playerNumber == 0 ? MatchState.FIRST_PLAYER_WON : MatchState.SECOND_PLAYER_WON;
        } else {
            throw new IllegalStateException("Cannot call pointWon method on ADVANTAGE");
        }

        return MatchState.ONGOING;
    }

    @Override
    public RegularGamePlayerPoints getZeroScore() {
        return RegularGamePlayerPoints.ZERO;
    }
}
