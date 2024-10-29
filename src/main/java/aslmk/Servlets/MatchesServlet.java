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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "MatchesServlet", value = "/matches")
public class MatchesServlet extends HttpServlet {
    FinishedMatchesPersistenceServiceImpl finishedMatchesPersistenceService = new FinishedMatchesPersistenceServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = Utils.getPageNumber(request.getParameter("page"));
        boolean isFilterApplied = false;
        boolean hasNextPage;
        List<Match> matches;
        String filterByName = request.getParameter("filter_by_player_name");

        if (!Utils.isValidString(filterByName)) {
            filterByName = "";
        } else {
            isFilterApplied = true;
            filterByName = filterByName.trim().toUpperCase();
        }

        try {
            if (isFilterApplied) {
                matches = finishedMatchesPersistenceService.getMatchesByPlayerName(filterByName, pageNumber);
                hasNextPage = finishedMatchesPersistenceService.hasNextPage(filterByName, pageNumber);
            } else {
                matches = finishedMatchesPersistenceService.getMatchesByPage(pageNumber);
                hasNextPage = finishedMatchesPersistenceService.hasNextPage(pageNumber);
            }
            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("allMatches", matches);
            request.setAttribute("hasNextPage", hasNextPage);
            request.setAttribute("isFilterApplied", isFilterApplied);
            request.setAttribute("filter_by_player_name", filterByName);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/matches.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            Utils.redirectToErrorPage(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(), request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = Utils.getPageNumber(request.getParameter("page"));
        boolean hasNextPage;
        boolean isFilterApplied = true;
        List<Match> matches;
        String filterByName = request.getParameter("filter_by_player_name");
        if (!Utils.isValidString(filterByName)) {
            filterByName = "";
        } else {
            filterByName = filterByName.trim().toUpperCase();
        }

        try {
            if (!filterByName.isEmpty()) {
                matches = finishedMatchesPersistenceService.getMatchesByPlayerName(filterByName, pageNumber);
                hasNextPage = finishedMatchesPersistenceService.hasNextPage(filterByName, pageNumber);
            } else {
                matches = finishedMatchesPersistenceService.getMatchesByPage(pageNumber);
                hasNextPage = finishedMatchesPersistenceService.hasNextPage(pageNumber);
                isFilterApplied = false;
            }


            request.setAttribute("allMatches", matches);
            request.setAttribute("hasNextPage", hasNextPage);
            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("isFilterApplied", isFilterApplied);
            request.setAttribute("filter_by_player_name", filterByName);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/matches.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            Utils.redirectToErrorPage(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(), request, response);
        }
    }
}
