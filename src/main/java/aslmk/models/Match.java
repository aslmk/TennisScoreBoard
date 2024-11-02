package aslmk.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "player1_id", nullable = false)
    private Player player1;
    @ManyToOne
    @JoinColumn(name = "player2_id", nullable = false)
    private Player player2;
    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Player winner;


    public Player getWinner() {
        return winner;
    }
    public Player getFirstPlayer() {
        return player1;
    }
    public Player getSecondPlayer() {
        return player2;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public void setFirstPlayer(Player player) {
        this.player1 = player;
    }
    public void setSecondPlayer(Player player) {
        this.player2 = player;
    }
}
