package aslmk.servlets;

import aslmk.models.Match;
import aslmk.services.Impl.FinishedMatchesPersistenceServiceImpl;
import aslmk.utils.Utils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.HibernateException;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MatchesServlet", value = "/matches")
public class MatchesServlet extends HttpServlet {
    FinishedMatchesPersistenceServiceImpl finishedMatchesPersistenceService = new FinishedMatchesPersistenceServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = Utils.getPageNumber(request.getParameter("page"));
        boolean hasNextPage;
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
            }

            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("allMatches", matches);
            request.setAttribute("hasNextPage", hasNextPage);
            request.setAttribute("filter_by_player_name", filterByName);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/matches.jsp");
            dispatcher.forward(request, response);
        } catch (HibernateException e) {
            Utils.redirectToErrorPage(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(), request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = Utils.getPageNumber(request.getParameter("page"));
        boolean hasNextPage;
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
            }


            request.setAttribute("allMatches", matches);
            request.setAttribute("hasNextPage", hasNextPage);
            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("filter_by_player_name", filterByName);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/matches.jsp");
            dispatcher.forward(request, response);

        } catch (HibernateException e) {
            Utils.redirectToErrorPage(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(), request, response);
        }
    }
}
