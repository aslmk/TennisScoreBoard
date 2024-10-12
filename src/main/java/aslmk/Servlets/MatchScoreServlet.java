package aslmk.Servlets;

import aslmk.DAO.PlayersDAO;
import aslmk.Exceptions.InvalidParametersException;
import aslmk.Models.Match;
import aslmk.Models.MatchScore;
import aslmk.Models.Player;
import aslmk.Services.Impl.FinishedMatchesPersistenceServiceImpl;
import aslmk.Services.Impl.MatchScoreCalculationServiceImpl;
import aslmk.Services.Impl.OngoingMatchesServiceImpl;
import aslmk.Utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScoreServlet", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    private MatchScoreCalculationServiceImpl matchScoreCalculationService = new MatchScoreCalculationServiceImpl();
    private FinishedMatchesPersistenceServiceImpl finishedMatchesPersistenceService = new FinishedMatchesPersistenceServiceImpl();
    private OngoingMatchesServiceImpl ongoingMatchesService = new OngoingMatchesServiceImpl();
    private PlayersDAO playersDAO = new PlayersDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  uuid = request.getParameter("uuid");
        String player = request.getParameter("playerId");

        try {
            UUID match_uuid = UUID.fromString(uuid);
            int playerId = Integer.parseInt(player);

            MatchScore matchScore = matchScoreCalculationService.updatePlayerScore(match_uuid, playerId);

            if (matchScoreCalculationService.isMatchWinner(matchScore, playerId)) {
                Match finishedMatch = matchScoreCalculationService.FinishedMatch(playersDAO.getPlayerById(playerId));
                finishedMatchesPersistenceService.saveMatchToDatabase(finishedMatch);

                request.setAttribute("winner", finishedMatch.getWinner());
                request.setAttribute("match_uuid", match_uuid.toString());
                request.setAttribute("match", finishedMatch);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/currentMatchResults.jsp");
                dispatcher.forward(request, response);
                response.sendRedirect("currentMatchResults.jsp");
                ongoingMatchesService.removeMatch(match_uuid);
            }

           setPlayerScores(matchScore, request);

           getServletContext().getRequestDispatcher("/match-score.jsp?uuid="+match_uuid).forward(request, response);

        } catch (InvalidParametersException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
    private void setPlayerScores(MatchScore matchScore, HttpServletRequest request) {
        int firstPlayerId = ongoingMatchesService.getFirstPlayerId();
        int secondPlayerId = ongoingMatchesService.getSecondPlayerId();
        request.setAttribute("firstPlayerName", playersDAO.getPlayerById(firstPlayerId).getName());
        request.setAttribute("firstPlayerPoints", matchScore.getPointsOfPlayer(firstPlayerId));
        request.setAttribute("firstPlayerGames", matchScore.getGamesOfPlayer(firstPlayerId));
        request.setAttribute("firstPlayerSets", matchScore.getSetsOfPlayer(firstPlayerId));
        request.setAttribute("secondPlayerName", playersDAO.getPlayerById(secondPlayerId).getName());
        request.setAttribute("secondPlayerPoints", matchScore.getPointsOfPlayer(secondPlayerId));
        request.setAttribute("secondPlayerGames", matchScore.getGamesOfPlayer(secondPlayerId));
        request.setAttribute("secondPlayerSets", matchScore.getSetsOfPlayer(secondPlayerId));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
