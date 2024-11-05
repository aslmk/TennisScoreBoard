package aslmk.servlets;

import aslmk.dto.MatchDTO;
import aslmk.exceptions.MatchNotFoundException;
import aslmk.exceptions.MatchSaveFailedException;
import aslmk.models.Player;
import aslmk.services.Impl.FinishedMatchesPersistenceServiceImpl;
import aslmk.services.Impl.MatchScoreCalculationServiceImpl;
import aslmk.services.Impl.OngoingMatchesServiceImpl;
import aslmk.services.Impl.matchScoreCalculation.CurrentMatchScore;
import aslmk.services.Impl.matchScoreCalculation.MatchState;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        UUID match_uuid = UUID.fromString(uuid);

        CurrentMatchScore currentMatchScore = ongoingMatchesService.getMatchByUUID(match_uuid);
        request.setAttribute("currentMatchScore", currentMatchScore);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/matchScore.jsp?uuid="+match_uuid);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String uuid = request.getParameter("uuid");
            String player = request.getParameter("playerId");
            UUID match_uuid = UUID.fromString(uuid);
            int playerId = Integer.parseInt(player);

            CurrentMatchScore currentMatchScore = ongoingMatchesService.getMatchByUUID(match_uuid);
            MatchState currentMatchState = matchScoreCalculationService.updateScore(playerId, currentMatchScore);

            if (currentMatchState != MatchState.ONGOING) {
                Player currentMatchWinner = matchScoreCalculationService.determineWinner(
                        currentMatchScore.getFirstPlayer(),
                        currentMatchScore.getSecondPlayer(),
                        currentMatchState);

                MatchDTO matchDTO = matchScoreCalculationService.createMatchDTO(
                        match_uuid,
                        currentMatchScore,
                        currentMatchWinner);

                finishedMatchesPersistenceService.saveMatchToDatabase(matchDTO);
                request.setAttribute("match", matchDTO);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/currentMatchResults.jsp");
                dispatcher.forward(request, response);
                ongoingMatchesService.removeMatch(match_uuid);
                return;
            }

            request.setAttribute("currentMatchScore", currentMatchScore);
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
