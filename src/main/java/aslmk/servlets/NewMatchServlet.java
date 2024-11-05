package aslmk.servlets;

import aslmk.exceptions.InvalidParametersException;
import aslmk.exceptions.PlayerSaveFailedException;
import aslmk.models.Player;
import aslmk.services.Impl.OngoingMatchesServiceImpl;
import aslmk.services.Impl.PlayersServiceImpl;
import aslmk.services.Impl.matchScoreCalculation.CurrentMatchScore;
import aslmk.utils.Utils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "NewMatchServlet", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private PlayersServiceImpl playersService = new PlayersServiceImpl();
    private OngoingMatchesServiceImpl ongoingMatchesService = new OngoingMatchesServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/newMatch.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstPlayerName = request.getParameter("player1").trim();
        String secondPlayerName = request.getParameter("player2").trim();

        try {
            if (!Utils.isValidString(firstPlayerName) ||
                    !Utils.isValidString(secondPlayerName)) {
                throw new InvalidParametersException("Enter valid player names");
            }
            if (firstPlayerName.equals(secondPlayerName)) {
                throw new InvalidParametersException("Player names should be different");
            }

            Player firstPlayer = playersService.createPlayerIfNotExists(firstPlayerName);
            Player secondPlayer = playersService.createPlayerIfNotExists(secondPlayerName);

            CurrentMatchScore currentMatch = ongoingMatchesService.createNewMatch(firstPlayer, secondPlayer);
            UUID match_uuid = currentMatch.getMatchUUID();

            response.sendRedirect("/match-score?uuid=" + match_uuid);
        } catch (InvalidParametersException e) {
            Utils.redirectToErrorPage(HttpServletResponse.SC_BAD_REQUEST, e.getMessage(), request, response);
        } catch (PlayerSaveFailedException e) {
            Utils.redirectToErrorPage(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(), request, response);
        }

    }
}
