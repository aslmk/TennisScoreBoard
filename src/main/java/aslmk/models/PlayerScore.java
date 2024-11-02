package aslmk.models;

import java.util.Objects;

public class PlayerScore {
    private int points;
    private int games;
    private int sets;
    private int tieBreakPoints;

    public PlayerScore() {
        this.points = 0;
        this.games = 0;
        this.sets = 0;
        this.tieBreakPoints = 0;

    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getTieBreakPoints() {
        return tieBreakPoints;
    }
    public void setTieBreakPoints(int tieBreakPoints) {
        this.tieBreakPoints = tieBreakPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerScore that = (PlayerScore) o;
        return points == that.points && games == that.games && sets == that.sets;
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, games, sets);
    }
}
