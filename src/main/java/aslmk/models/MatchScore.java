package aslmk.models;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MatchScore {
    private ConcurrentHashMap<Integer, PlayerScore> playerScores;
    private int advantage;
    private Player firstPlayer;
    private Player secondPlayer;

    public MatchScore (Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        int firstPlayerId = firstPlayer.getId();
        int secondPlayerId = secondPlayer.getId();
        playerScores = new ConcurrentHashMap<>();
        playerScores.put(firstPlayerId, new PlayerScore());
        playerScores.put(secondPlayerId, new PlayerScore());
        this.advantage = -1;
    }
    public void incrementPointsOfPlayer(int playerId, int value) {
        PlayerScore player = playerScores.get(playerId);
        int currentPointsCount = player.getPoints();
        player.setPoints(currentPointsCount + value);
        playerScores.put(playerId, player);
    }
    public int getPointsOfPlayer (int playerId){
        return playerScores.get(playerId).getPoints();
    }
    public void resetPointsOfPlayer (int playerId){
        PlayerScore player = playerScores.get(playerId);
        player.setPoints(0);
    }
    public void resetPlayersPoints() {
        playerScores.get(firstPlayer.getId()).setPoints(0);
        playerScores.get(secondPlayer.getId()).setPoints(0);
    }

    public void incrementGamesOfPlayer(int playerId) {
        PlayerScore player = playerScores.get(playerId);
        int currentGamesCount = player.getGames();
        player.setGames(currentGamesCount + 1);
        playerScores.put(playerId, player);
    }
    public int getGamesOfPlayer (int playerId) {
        return playerScores.get(playerId).getGames();
    }
    public void resetPlayerGames() {
        playerScores.get(firstPlayer.getId()).setGames(0);
        playerScores.get(secondPlayer.getId()).setGames(0);
    }

    public void incrementSetsOfPlayer(int playerId) {
        PlayerScore player = playerScores.get(playerId);
        int currentSetsCount = player.getSets();
        player.setSets(currentSetsCount + 1);
    }
    public int getSetsOfPlayer (int playerId){
        return playerScores.get(playerId).getSets();
    }

    public int getAdvantageOfPlayer() {
        return advantage;
    }
    public void setAdvantageOfPlayer(int advantage) {
        this.advantage = advantage;
    }
    public void resetAdvantage() {
        advantage = -1;
    }

    public void incrementTieBreakPointsOfPlayer (int playerId) {
        int currentTieBreakPoints = playerScores.get(playerId).getTieBreakPoints();
        playerScores.get(playerId).setTieBreakPoints(currentTieBreakPoints + 1);
    }
    public int getTieBreakPoints(int playerId) {
        return playerScores.get(playerId).getTieBreakPoints();
    }

    public PlayerScore getPlayerScore(int playerId) {
        return playerScores.get(playerId);
    }
    public Player getFirstPlayer() {
        return firstPlayer;
    }
    public Player getSecondPlayer() {
        return secondPlayer;
    }
    public int getOpponentId(int playerId) {
        return firstPlayer.getId() == playerId ? secondPlayer.getId() : firstPlayer.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchScore that = (MatchScore) o;
        return advantage == that.advantage && firstPlayer == that.firstPlayer && secondPlayer == that.secondPlayer && Objects.equals(playerScores, that.playerScores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerScores, advantage, firstPlayer, secondPlayer);
    }
}

