package aslmk.junit5;

import aslmk.services.Impl.matchScoreCalculation.MatchState;
import aslmk.services.Impl.matchScoreCalculation.TieBreakScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TieBreakTest {

    @Test
    public void firstPlayerWonTieBreak() {
        TieBreakScore tieBreakScore = new TieBreakScore();
        for (int i = 0; i < 6; i++) {
            tieBreakScore.pointWon(0);
        }
        Assertions.assertEquals(MatchState.FIRST_PLAYER_WON, tieBreakScore.pointWon(0));
    }

    @Test
    public void gameNotEndedAfterSevenPoints() {
        TieBreakScore tieBreakScore = new TieBreakScore();
        for (int i = 0; i < 6; i++) {
            tieBreakScore.pointWon(0);
            tieBreakScore.pointWon(1);
        }
        // Current score will be 7 : 6. Game not ended.
        Assertions.assertEquals(MatchState.ONGOING, tieBreakScore.pointWon(0));

        // Current score 8 : 6. First player won game.
        Assertions.assertEquals(MatchState.FIRST_PLAYER_WON, tieBreakScore.pointWon(0));
    }
}
