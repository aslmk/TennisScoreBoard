package aslmk.Servlets;

import aslmk.Models.Match;
import aslmk.Services.Impl.FinishedMatchesPersistenceServiceImpl;
import aslmk.Services.Impl.MatchesServiceImpl;
import aslmk.Services.Impl.PlayersServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.annotations.processing.SQL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MatchesServlet", value = "/matches")
public class MatchesServlet extends HttpServlet {
    PlayersServiceImpl playersService = new PlayersServiceImpl();
    MatchesServiceImpl matchesService = new MatchesServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String filterByName = request.getParameter("filter_by_player_name");
        //Players player =  playersService.findByName(filterByName);

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
            RequestDispatcher dispatcher = request.getRequestDispatcher("/matches.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
