package aslmk.Servlets;

import aslmk.Exceptions.MatchNotFoundException;
import aslmk.Exceptions.MatchSaveFailedException;
import aslmk.Models.Match;
import aslmk.Models.MatchScore;
import aslmk.Models.Player;
import aslmk.Services.Impl.FinishedMatchesPersistenceServiceImpl;
import aslmk.Services.Impl.MatchScoreCalculationServiceImpl;
import aslmk.Services.Impl.OngoingMatchesServiceImpl;
import aslmk.Services.Impl.PlayersServiceImpl;
import aslmk.Utils.Utils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.rmi.server.UID;
import java.util.UUID;

@WebServlet(name = "MatchScoreServlet", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    private MatchScoreCalculationServiceImpl matchScoreCalculationService = new MatchScoreCalculationServiceImpl();
    private FinishedMatchesPersistenceServiceImpl finishedMatchesPersistenceService = new FinishedMatchesPersistenceServiceImpl();
    private OngoingMatchesServiceImpl ongoingMatchesService = new OngoingMatchesServiceImpl();
    private PlayersServiceImpl playersService = new PlayersServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        UUID match_uuid = UUID.fromString(uuid);

        MatchScore currentMatch = ongoingMatchesService.getMatchByUUID(match_uuid);
        request.setAttribute("currentMatch", currentMatch);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/matchScore.jsp?uuid="+match_uuid);
        dispatcher.forward(request, response);

    }
    private void setFinishedMatchInformation(HttpServletRequest request, Match finishedMatch, UUID match_uuid) {
        request.setAttribute("winner", finishedMatch.getWinner());
        request.setAttribute("match_uuid", match_uuid.toString());
        request.setAttribute("match", finishedMatch);
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
                setFinishedMatchInformation(request, finishedMatch, match_uuid);
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
