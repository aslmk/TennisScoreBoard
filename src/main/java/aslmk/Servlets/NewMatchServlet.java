package aslmk.Servlets;

import aslmk.Exceptions.InvalidParametersException;
import aslmk.Models.Player;
import aslmk.Services.Impl.OngoingMatchesServiceImpl;
import aslmk.Services.Impl.PlayersServiceImpl;
import aslmk.Utils.Utils;
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
            if (firstPlayerName.equals(secondPlayerName) ||
                    !Utils.isValidString(firstPlayerName) ||
                    !Utils.isValidString(secondPlayerName)) {
                throw new InvalidParametersException("Enter first player name and second player name");
            }

            Player firstPlayer = playersService.createPlayerIfNotExists(firstPlayerName);
            Player secondPlayer = playersService.createPlayerIfNotExists(secondPlayerName);

            ongoingMatchesService.createNewMatch(firstPlayer, secondPlayer);
            UUID match_uuid = ongoingMatchesService.getUuidOfMatch();

            setDefaultPlayersScore(request, firstPlayer, secondPlayer);
            String newUrl = request.getContextPath() + "/matchScore.jsp?uuid=" + match_uuid;

            RequestDispatcher dispatcher = request.getRequestDispatcher(newUrl);
            dispatcher.forward(request, response);
        } catch (InvalidParametersException e) {
            Utils.redirectToErrorPage(HttpServletResponse.SC_BAD_REQUEST, e.getMessage(), request, response);
        }

    }
    private static void setDefaultPlayersScore(HttpServletRequest request, Player player1, Player player2) {
        request.setAttribute("firstPlayerId", player1.getId());
        request.setAttribute("secondPlayerId", player2.getId());
        request.setAttribute("firstPlayerName", player1.getName());
        request.setAttribute("secondPlayerName", player2.getName());
        request.setAttribute("firstPlayerPoints", 0);
        request.setAttribute("secondPlayerPoints", 0);
        request.setAttribute("firstPlayerGames", 0);
        request.setAttribute("secondPlayerGames", 0);
        request.setAttribute("firstPlayerSets", 0);
        request.setAttribute("secondPlayerSets", 0);
    }
}
