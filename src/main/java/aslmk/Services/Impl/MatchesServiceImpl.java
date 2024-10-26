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

    @Override
    public List<Match> getAllMatchesByPage(int page) throws SQLException {
        return matchesDAO.getAllMatchesByPage(page);
    }

    @Override
    public List<Match> getMatchesByPlayerId(int playerId) throws SQLException {
        return matchesDAO.getMatchesByPlayerId(playerId);
    }

    @Override
    public boolean hasNextPage(int playerId, int pageNumber) throws SQLException {
        return matchesDAO.hasNextPage(playerId, pageNumber);
    }
    @Override
    public boolean hasNextPage(int pageNumber) throws SQLException {
        return matchesDAO.hasNextPage(pageNumber);
    }
}
