package aslmk.Servlets;

import aslmk.Models.Match;
import aslmk.Models.Player;
import aslmk.Services.Impl.FinishedMatchesPersistenceServiceImpl;
import aslmk.Services.Impl.PlayersServiceImpl;
import aslmk.Utils.Utils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MatchesServlet", value = "/matches")
public class MatchesServlet extends HttpServlet {
    PlayersServiceImpl playersService = new PlayersServiceImpl();
    FinishedMatchesPersistenceServiceImpl finishedMatchesPersistenceService = new FinishedMatchesPersistenceServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = Utils.getPageNumber(request.getParameter("page"));

        try {
            List<Match> matches = finishedMatchesPersistenceService.getMatchesByPage(pageNumber);
            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("allMatches", matches);
            request.setAttribute("hasNextPage", finishedMatchesPersistenceService.hasNextPage(pageNumber));
            request.setAttribute("isFilterApplied", false);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/matches.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = Utils.getPageNumber(request.getParameter("page"));
        boolean hasNextPage;
        boolean isFilterApplied;
        int playerId;
        List<Match> matches;
        String filterByName = request.getParameter("filter_by_player_name");

        try {
            Player player =  playersService.findByName(filterByName.trim().toUpperCase());
            if (player != null) {
                playerId = player.getId();
                matches = finishedMatchesPersistenceService.getMatchesByPlayerId(playerId);
                hasNextPage = finishedMatchesPersistenceService.hasNextPage(playerId, pageNumber);
                isFilterApplied = true;
            } else {
                matches = finishedMatchesPersistenceService.getMatchesByPage(pageNumber);
                hasNextPage = finishedMatchesPersistenceService.hasNextPage(pageNumber);
                isFilterApplied = false;
            }
            request.setAttribute("allMatches", matches);
            request.setAttribute("hasNextPage", hasNextPage);
            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("isFilterApplied", isFilterApplied);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/matches.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            Utils.redirectToErrorPage(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(), request, response);
        }
    }
}
