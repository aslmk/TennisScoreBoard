package aslmk.Services.Impl;

import aslmk.DAO.MatchesDAO;
import aslmk.Models.Match;
import aslmk.Services.MatchesService;

import java.sql.SQLException;
import java.util.List;

public class MatchesServiceImpl implements MatchesService {
    MatchesDAO matchesDAO = new MatchesDAO();
    @Override
    public List<Match> getAllMatches() throws SQLException {
        return matchesDAO.getAllMatches();
    }
}
