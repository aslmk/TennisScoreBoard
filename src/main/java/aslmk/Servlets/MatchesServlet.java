package aslmk.Servlets;

import aslmk.Services.Impl.MatchesServiceImpl;
import aslmk.Services.Impl.PlayersServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MatchesServlet", value = "/matches")
public class MatchesServlet extends HttpServlet {
    PlayersServiceImpl playersService = new PlayersServiceImpl();
    MatchesServiceImpl matchesService = new MatchesServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String filterByName = request.getParameter("filter_by_player_name");
        //Players player =  playersService.findByName(filterByName);
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), matchesService.getAllMatches());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
