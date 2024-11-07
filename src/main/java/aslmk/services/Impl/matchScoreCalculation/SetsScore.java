package aslmk.services.Impl.matchScoreCalculation;

public class SetsScore extends Score<Integer> {
    private Score<?> currentGame;

    public SetsScore() {
        this.currentGame = new RegularGameScore();
    }

    @Override
    public MatchState pointWon(int playerNumber) {
        MatchState matchState = currentGame.pointWon(playerNumber);
        if (matchState == MatchState.FIRST_PLAYER_WON) {
            return gameWon(0);
        } else if (matchState == MatchState.SECOND_PLAYER_WON) {
            return gameWon(1);
        }
        return MatchState.ONGOING;
    }

    public MatchState gameWon(int playerNumber) {
        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);
        this.currentGame = new RegularGameScore();

        int opponentScore = getOpponentScore(playerNumber);
        int scoreDiff = getPlayerScore(playerNumber) - opponentScore;

        if (getPlayerScore(playerNumber) >= 6 && scoreDiff >= 2) {
            // player won game
            return playerNumber == 0 ? MatchState.FIRST_PLAYER_WON : MatchState.SECOND_PLAYER_WON;

        } else if (getPlayerScore(playerNumber) == 6 && getOpponentScore(playerNumber) == 6) {
            this.currentGame = new TieBreakScore();
        }
        return MatchState.ONGOING;

    }

    public String getRegularGamePlayerPoints(int playerNumber) {
        if (currentGame instanceof RegularGameScore) {
            RegularGamePlayerPoints points = ((RegularGamePlayerPoints) currentGame.getPlayerScore(playerNumber));
            return points.getPoints();
        } else if (currentGame instanceof TieBreakScore) {
            return String.valueOf(currentGame.getPlayerScore(playerNumber));
        }
        return "";
    }

    @Override
    public Integer getZeroScore() {
        return 0;
    }
}
