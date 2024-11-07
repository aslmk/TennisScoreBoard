package aslmk.junit5;

import aslmk.services.Impl.matchScoreCalculation.MatchState;
import aslmk.services.Impl.matchScoreCalculation.RegularGamePlayerPoints;
import aslmk.services.Impl.matchScoreCalculation.RegularGameScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegularGameScoreTest {
    @Test
    public void testFirstPlayerWonGame() {
        RegularGameScore regularGameScore = new RegularGameScore();
        for (int i = 0; i < 3; i++) {
            regularGameScore.pointWon(0);
        }
        Assertions.assertEquals(MatchState.FIRST_PLAYER_WON, regularGameScore.pointWon(0));
    }

    @Test
    public void testAdvantage() {
        RegularGameScore regularGameScore = new RegularGameScore();
        for (int i = 0; i < 3; i++) {
            regularGameScore.pointWon(0);
            regularGameScore.pointWon(1);
        }
        // Game not ended after 40 : 40
        Assertions.assertEquals(MatchState.ONGOING, regularGameScore.pointWon(0));

        // First player got advantage
        Assertions.assertEquals(RegularGamePlayerPoints.ADVANTAGE, regularGameScore.getPlayerScore(0));

        // Game not ended after advantage
        Assertions.assertEquals(MatchState.ONGOING, regularGameScore.pointWon(1));

        // Current score will be 40 : 40
        Assertions.assertEquals(RegularGamePlayerPoints.FORTY, regularGameScore.getPlayerScore(0));
        Assertions.assertEquals(RegularGamePlayerPoints.FORTY, regularGameScore.getPlayerScore(1));

        // Second player got advantage
        Assertions.assertEquals(MatchState.ONGOING, regularGameScore.pointWon(1));
        Assertions.assertEquals(RegularGamePlayerPoints.ADVANTAGE, regularGameScore.getPlayerScore(1));

        // Second player wins game
        Assertions.assertEquals(MatchState.SECOND_PLAYER_WON, regularGameScore.pointWon(1));
    }
}
