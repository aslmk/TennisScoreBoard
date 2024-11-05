package aslmk.dto;

import aslmk.models.Player;

import java.util.UUID;

public class MatchDTO {
    private UUID match_uuid;
    private Player firstPlayer;
    private Player secondPlayer;
    private Player winner;

    public UUID getUUID() {
        return match_uuid;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public MatchDTO(UUID match_uuid, Player player1, Player player2, Player winner) {
        this.match_uuid = match_uuid;
        this.firstPlayer = player1;
        this.secondPlayer = player2;
        this.winner = winner;
    }

}
