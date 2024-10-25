package aslmk.Servlets;

import aslmk.Models.Player;
import aslmk.Services.Impl.OngoingMatchesServiceImpl;
import aslmk.Services.Impl.PlayersServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name = "NewMatchServlet", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private PlayersServiceImpl playersService = new PlayersServiceImpl();
    private OngoingMatchesServiceImpl ongoingMatchesService = new OngoingMatchesServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String player1Name = request.getParameter("player1");
        String player2Name = request.getParameter("player2");

        if (player1Name.equals(player2Name)) {
            //PlayersNameException;
            return;
        }

        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);

        if (playersService.findByName(player1.getName()) == null) {
            playersService.createPlayer(player1);
        }
        if (playersService.findByName(player2.getName()) == null) {
            playersService.createPlayer(player2);
        }


        ongoingMatchesService.createNewMatch(player1, player2);
        UUID match_uuid = ongoingMatchesService.getUuidOfMatch();

        setDefaultPlayersScore(request, player1, player2);
        String newUrl = request.getContextPath() + "/matchScore.jsp?uuid=" + match_uuid;

        RequestDispatcher dispatcher = request.getRequestDispatcher(newUrl);
        dispatcher.forward(request, response);
        //response.sendRedirect(newUrl);
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
