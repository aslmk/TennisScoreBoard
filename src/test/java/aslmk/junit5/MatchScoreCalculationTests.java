package aslmk.junit5;

import aslmk.models.MatchScore;
import aslmk.models.Player;
import aslmk.services.Impl.MatchScoreCalculationServiceImpl;
import aslmk.services.Impl.PlayersServiceImpl;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.hibernate.cfg.Configuration;

public class MatchScoreCalculationTests {
    private SessionFactory sessionFactory;
    private PlayersServiceImpl playersService = new PlayersServiceImpl();
    private MatchScoreCalculationServiceImpl matchScoreCalculationService = new MatchScoreCalculationServiceImpl();

    @BeforeEach
    public void initDatabase() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @Test
    void testDeuce() {
        Player player1 = playersService.createPlayerIfNotExists("Player1");
        Player player2 = playersService.createPlayerIfNotExists("Player2");

        MatchScore matchScore = new MatchScore(player1, player2);
        matchScore.getPlayerScore(player1.getId()).setPoints(40);

        matchScore.getPlayerScore(player2.getId()).setPoints(40);
        boolean isDeuce = matchScoreCalculationService.isDeuce(matchScore, player1.getId(), player2.getId());
        Assertions.assertTrue(isDeuce);
    }

    @Test
    void testPlayerWinsGame() {
        Player player3 = playersService.createPlayerIfNotExists("Player3");
        Player player4 = playersService.createPlayerIfNotExists("Player4");
        MatchScore matchScore = new MatchScore(player3, player4);
        matchScore.getPlayerScore(player3.getId()).setPoints(40);
        boolean isGameWinner = matchScoreCalculationService.isGameWinner(matchScore, player3.getId(), player4.getId());
        Assertions.assertTrue(isGameWinner);
    }

    @Test
    void testTieBreak() {
        Player player5 = playersService.createPlayerIfNotExists("Player5");
        Player player6 = playersService.createPlayerIfNotExists("Player6");
        MatchScore matchScore = new MatchScore(player5, player6);
        matchScore.getPlayerScore(player5.getId()).setGames(6);
        matchScore.getPlayerScore(player6.getId()).setGames(6);
        boolean isGameWinner = matchScoreCalculationService.isTieBreak(matchScore, player5.getId(), player6.getId());
        Assertions.assertTrue(isGameWinner);

    }

    @AfterEach
    void closeDatabase() {
        sessionFactory.close();
    }
}
