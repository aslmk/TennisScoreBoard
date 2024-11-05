package aslmk.services.Impl.matchScoreCalculation;

import java.util.ArrayList;
import java.util.List;

public abstract class Score<T> {
    List<T> playerScores = new ArrayList<>();

    protected abstract T getZeroScore();

    public Score() {
        playerScores.add(getZeroScore());
        playerScores.add(getZeroScore());
    }

     public T getPlayerScore(int playerNumber) {
        return playerScores.get(playerNumber);
    }

    public void setPlayerScore(int playerNumber, T playerScore) {
        playerScores.set(playerNumber, playerScore);
    }
    public void resetPlayerScore(int playerNumber) {
        playerScores.set(playerNumber, getZeroScore());
    }

    public T getOpponentScore(int playerNumber) {
        return playerScores.get(playerNumber == 0 ? 1 : 0);
    }

    public T setOpponentScore(int playerNumber, T playerScore) {
        return playerScores.set(playerNumber == 0 ? 1 : 0, playerScore);
    }

    abstract MatchState pointWon(int playerNumber);

}