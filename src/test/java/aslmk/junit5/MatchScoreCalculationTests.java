package aslmk.junit5;

import aslmk.Models.MatchScore;
import aslmk.Models.Player;
import aslmk.Services.Impl.MatchScoreCalculationServiceImpl;
import aslmk.Services.Impl.PlayersServiceImpl;
import org.hibernate.Session;
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
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        playersService.createPlayer(player1);
        playersService.createPlayer(player2);

        MatchScore matchScore = new MatchScore(player1.getId(), player2.getId());
        matchScore.getPlayerScore(player1.getId()).setPoints(40);

        matchScore.getPlayerScore(player2.getId()).setPoints(40);
        boolean isDeuce = matchScoreCalculationService.isDeuce(matchScore, player1.getId(), player2.getId());
        Assertions.assertTrue(isDeuce);
    }

    @Test
    void testPlayerWinsGame() {
        Player player3 = new Player("Player3");
        Player player4 = new Player("Player4");
        playersService.createPlayer(player3);
        playersService.createPlayer(player4);
        MatchScore matchScore = new MatchScore(player3.getId(), player4.getId());
        matchScore.getPlayerScore(player3.getId()).setPoints(40);
        boolean isGameWinner = matchScoreCalculationService.isGameWinner(matchScore, player3.getId(), player4.getId());
        Assertions.assertTrue(isGameWinner);
    }

    @Test
    void testTieBreak() {
        Player player5 = new Player("Player5");
        Player player6 = new Player("Player6");
        playersService.createPlayer(player5);
        playersService.createPlayer(player6);
        MatchScore matchScore = new MatchScore(player5.getId(), player6.getId());
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
