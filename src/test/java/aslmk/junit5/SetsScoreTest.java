package aslmk.junit5;

import aslmk.services.Impl.matchScoreCalculation.MatchState;
import aslmk.services.Impl.matchScoreCalculation.SetsScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetsScoreTest {

    @Test
    public void testFirstPlayerWonSet() {
        SetsScore setsScore = new SetsScore();
        for (int i = 0; i < 23; i++) {
            setsScore.pointWon(0);
        }
        Assertions.assertEquals(MatchState.FIRST_PLAYER_WON, setsScore.pointWon(0));
    }

    @Test
    public void testTieBreak() {
        SetsScore setsScore = new SetsScore();

        for (int i = 0; i < 6; i++) {
            setsScore.gameWon(0);
            setsScore.gameWon(1);
        }
        // Tie-break
        Assertions.assertEquals(6, setsScore.getPlayerScore(0));
        Assertions.assertEquals(6, setsScore.getPlayerScore(1));
    }
}
