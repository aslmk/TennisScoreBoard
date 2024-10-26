package aslmk.Servlets;

import aslmk.Models.Match;
import aslmk.Models.Player;
import aslmk.Services.Impl.MatchesServiceImpl;
import aslmk.Services.Impl.PlayersServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MatchesServlet", value = "/matches")
public class MatchesServlet extends HttpServlet {
    PlayersServiceImpl playersService = new PlayersServiceImpl();
    MatchesServiceImpl matchesService = new MatchesServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber;
        try {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            pageNumber = 1;
        }
        if (pageNumber < 1) {
            pageNumber = 1;
        }

        try {
            //List<Match> matches = matchesService.getAllMatches();
            List<Match> matches = matchesService.getAllMatchesByPage(pageNumber);
            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("allMatches", matches);
            request.setAttribute("hasNextPage", matchesService.hasNextPage(pageNumber));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/matches.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber;
        boolean hasNextPage;
        int playerId = -1;

        try {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            pageNumber = 1;
        }
        if (pageNumber < 1) {
            pageNumber = 1;
        }

        String filterByName = request.getParameter("filter_by_player_name");

        try {
            Player player =  playersService.findByName(filterByName);

            if (filterByName == null) {
                hasNextPage = matchesService.hasNextPage(pageNumber);
            } else {
                playerId = player.getId();
                hasNextPage = matchesService.hasNextPage(playerId, pageNumber);
            }

            List<Match> matches = matchesService.getMatchesByPlayerId(playerId);
            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("allMatches", matches);
            request.setAttribute("hasNextPage", hasNextPage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/matches.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
