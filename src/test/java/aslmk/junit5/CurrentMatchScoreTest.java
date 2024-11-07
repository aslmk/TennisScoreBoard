package aslmk.junit5;

import aslmk.models.Player;
import aslmk.services.Impl.matchScoreCalculation.CurrentMatchScore;
import aslmk.services.Impl.matchScoreCalculation.MatchState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CurrentMatchScoreTest {

    @Test
    public void firstPlayerWinsMatch() {
        UUID testUUID = UUID.randomUUID();
        Player testFirstPlayer = new Player();
        Player testSecondPlayer = new Player();
        CurrentMatchScore currentMatchScore = new CurrentMatchScore(testUUID, testFirstPlayer, testSecondPlayer);

        // First player wins 1 out of 3 sets.
        for (int i = 0; i < 23; i++) {
            currentMatchScore.pointWon(0);
        }
        Assertions.assertEquals(MatchState.ONGOING, currentMatchScore.pointWon(0));
        Assertions.assertEquals(1, currentMatchScore.getPlayerScore(0));
        Assertions.assertEquals(0, currentMatchScore.getPlayerScore(1));

        // First player wins 2 out of 3 sets.
        for (int i = 0; i < 23; i++) {
            currentMatchScore.pointWon(0);
        }
        Assertions.assertEquals(MatchState.FIRST_PLAYER_WON, currentMatchScore.pointWon(0));
    }
}
