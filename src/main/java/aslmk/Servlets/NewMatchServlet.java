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

        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);

        playersService.createPlayer(player1);
        playersService.createPlayer(player2);

        ongoingMatchesService.createNewMatch(player1, player2);
        UUID match_uuid = ongoingMatchesService.getUuidOfMatch();

        String newUrl = request.getContextPath() + "/match-score.jsp?uuid=" + match_uuid;
        System.out.println("UUID="+match_uuid);
        System.out.println("player1Id="+player1.getId());
        System.out.println("player2Id="+player2.getId());
        response.sendRedirect(newUrl);
    }
}
