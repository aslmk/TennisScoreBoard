package aslmk.Models;

import java.util.HashMap;
import java.util.Objects;

public class MatchScore {
    private HashMap<Integer, PlayerScore> playerScores;
    private int advantage;
    private int firstPlayerId;
    private int secondPlayerId;

    public MatchScore (int player1Id, int player2Id) {
        firstPlayerId = player1Id;
        secondPlayerId = player2Id;
        playerScores = new HashMap<>();
        playerScores.put(firstPlayerId, new PlayerScore());
        playerScores.put(secondPlayerId, new PlayerScore());
        this.advantage = -1;
    }
    public int getPointsOfPlayer (int playerId){
        return playerScores.get(playerId).getPoints();
    }
    public void incrementPointsOfPlayer(int playerId, int value) {
        PlayerScore player = playerScores.get(playerId);
        int currentPointsCount = player.getPoints();
        player.setPoints(currentPointsCount + value);
        playerScores.put(playerId, player);
    }
    public void incrementGamesOfPlayer(int playerId) {
        PlayerScore player = playerScores.get(playerId);
        int currentGamesCount = player.getGames();
        player.setGames(currentGamesCount + 1);
        playerScores.put(playerId, player);
    }
    public void incrementSetsOfPlayer(int playerId) {
        PlayerScore player = playerScores.get(playerId);
        int currentSetsCount = player.getSets();
        player.setSets(currentSetsCount + 1);
    }
    public int getSetsOfPlayer (int playerId){
        return playerScores.get(playerId).getSets();
    }
    public int getGamesOfPlayer (int playerId) {
        return playerScores.get(playerId).getGames();
    }
    public void resetPointsOfPlayer (int playerId){
        PlayerScore player = playerScores.get(playerId);
        player.setPoints(0);
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
    public void resetPlayersPoints() {
        playerScores.get(firstPlayerId).setPoints(0);
        playerScores.get(secondPlayerId).setPoints(0);
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
    public void resetPlayerGames() {
        playerScores.get(firstPlayerId).setGames(0);
        playerScores.get(secondPlayerId).setGames(0);
    }

    public int getFirstPlayerId() {
        return firstPlayerId;
    }
    public int getSecondPlayerId() {
        return secondPlayerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchScore that = (MatchScore) o;
        return advantage == that.advantage && firstPlayerId == that.firstPlayerId && secondPlayerId == that.secondPlayerId && Objects.equals(playerScores, that.playerScores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerScores, advantage, firstPlayerId, secondPlayerId);
    }


}

