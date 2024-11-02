package aslmk.servlets;

import aslmk.exceptions.MatchNotFoundException;
import aslmk.exceptions.MatchSaveFailedException;
import aslmk.models.Match;
import aslmk.models.MatchScore;
import aslmk.services.Impl.FinishedMatchesPersistenceServiceImpl;
import aslmk.services.Impl.MatchScoreCalculationServiceImpl;
import aslmk.services.Impl.OngoingMatchesServiceImpl;
import aslmk.services.Impl.PlayersServiceImpl;
import aslmk.utils.Utils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScoreServlet", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final MatchScoreCalculationServiceImpl matchScoreCalculationService = new MatchScoreCalculationServiceImpl();
    private final FinishedMatchesPersistenceServiceImpl finishedMatchesPersistenceService = new FinishedMatchesPersistenceServiceImpl();
    private final OngoingMatchesServiceImpl ongoingMatchesService = new OngoingMatchesServiceImpl();
    private final PlayersServiceImpl playersService = new PlayersServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        UUID match_uuid = UUID.fromString(uuid);

        MatchScore currentMatch = ongoingMatchesService.getMatchByUUID(match_uuid);
        request.setAttribute("currentMatch", currentMatch);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/matchScore.jsp?uuid="+match_uuid);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String player = request.getParameter("playerId");

        try {
            UUID match_uuid = UUID.fromString(uuid);
            MatchScore currentMatch;
            int playerId = Integer.parseInt(player);

            currentMatch = matchScoreCalculationService.updatePlayerScore(match_uuid, playerId);

            if (matchScoreCalculationService.isMatchWinner(currentMatch, playerId)) {
                Match finishedMatch = matchScoreCalculationService.FinishedMatch(playersService.getPlayerById(playerId), currentMatch);
                finishedMatchesPersistenceService.saveMatchToDatabase(finishedMatch);
                request.setAttribute("match_uuid", match_uuid.toString());
                request.setAttribute("match", finishedMatch);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/currentMatchResults.jsp");
                dispatcher.forward(request, response);
                ongoingMatchesService.removeMatch(match_uuid);
                return;
            }

            request.setAttribute("currentMatch", currentMatch);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/matchScore.jsp?uuid="+match_uuid);
            dispatcher.forward(request, response);

        } catch (MatchNotFoundException e) {
            Utils.redirectToErrorPage(HttpServletResponse.SC_NOT_FOUND, e.getMessage(), request, response);
        } catch (MatchSaveFailedException e) {
            Utils.redirectToErrorPage(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(), request, response);
        } catch (IllegalArgumentException e) {
            Utils.redirectToErrorPage(HttpServletResponse.SC_BAD_REQUEST, e.getMessage(), request, response);
        }
    }

}
