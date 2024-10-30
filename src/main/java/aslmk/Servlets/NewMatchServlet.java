package aslmk.Servlets;

import aslmk.Exceptions.InvalidParametersException;
import aslmk.Exceptions.PlayerSaveFailedException;
import aslmk.Models.Player;
import aslmk.Services.Impl.OngoingMatchesServiceImpl;
import aslmk.Services.Impl.PlayersServiceImpl;
import aslmk.Utils.Utils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.HibernateException;

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

            UUID match_uuid = ongoingMatchesService.createNewMatch(firstPlayer, secondPlayer);

            response.sendRedirect("/match-score?uuid=" + match_uuid);
        } catch (InvalidParametersException e) {
            Utils.redirectToErrorPage(HttpServletResponse.SC_BAD_REQUEST, e.getMessage(), request, response);
        } catch (PlayerSaveFailedException e) {
            Utils.redirectToErrorPage(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(), request, response);
        }

    }
}
