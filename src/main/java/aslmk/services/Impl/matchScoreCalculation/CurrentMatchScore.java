package aslmk.services.Impl.matchScoreCalculation;

import aslmk.models.Player;
import java.util.Objects;
import java.util.UUID;

public class CurrentMatchScore extends Score<Integer> {
    private UUID match_UUID;
    private SetsScore setsScore;
    private Player firstPlayer;
    private Player secondPlayer;
    public CurrentMatchScore(UUID match_UUID, Player firstPlayer, Player secondPlayer) {
        this.match_UUID = match_UUID;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        setsScore = new SetsScore();
    }
    public UUID getMatchUUID() {
        return match_UUID;
    }
    public Player getFirstPlayer() {
        return firstPlayer;
    }
    public Player getSecondPlayer() {
        return secondPlayer;
    }
    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public MatchState pointWon(int playerNumber) {
        MatchState matchState = setsScore.pointWon(playerNumber);
        if (matchState == MatchState.FIRST_PLAYER_WON) {
            return setWon(0);
        } else if (matchState == MatchState.SECOND_PLAYER_WON) {
            return setWon(1);
        }
        return MatchState.ONGOING;
    }
    public MatchState setWon(int playerNumber) {
        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);

        if (getPlayerScore(0) == 2) {
            return MatchState.FIRST_PLAYER_WON;
        } else if (getPlayerScore(1) == 2) {
            return MatchState.SECOND_PLAYER_WON;
        }

        setsScore = new SetsScore();
        return MatchState.ONGOING;
    }

    public Integer getGameScore(int playerNumber) {
        return setsScore.getPlayerScore(playerNumber);
    }
    public String getPlayerPoints(int playerNumber) {
        return setsScore.getRegularGamePlayerPoints(playerNumber);
    }
    public Integer getSetsScore(int playerNumber) {
        return getPlayerScore(playerNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentMatchScore that = (CurrentMatchScore) o;
        return Objects.equals(match_UUID, that.match_UUID) && Objects.equals(setsScore, that.setsScore) && Objects.equals(firstPlayer, that.firstPlayer) && Objects.equals(secondPlayer, that.secondPlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(match_UUID, setsScore, firstPlayer, secondPlayer);
    }
}
