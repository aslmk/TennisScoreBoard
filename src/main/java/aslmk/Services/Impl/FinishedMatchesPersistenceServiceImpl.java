package aslmk.Services.Impl;

import aslmk.DAO.MatchesDAO;
import aslmk.Exceptions.MatchSaveFailedException;
import aslmk.Models.Match;
import aslmk.Services.FinishedMatchesPersistenceService;

import java.sql.SQLException;
import java.util.List;

public class FinishedMatchesPersistenceServiceImpl implements FinishedMatchesPersistenceService {
    MatchesDAO matchesDAO = new MatchesDAO();
    @Override
    public void saveMatchToDatabase(Match match) throws MatchSaveFailedException {
        matchesDAO.saveMatchToDatabase(match);
    }
    @Override
    public List<Match> getMatchesByPage(int page) throws SQLException {
        return matchesDAO.getMatchesByPage(page);
    }

    @Override
    public List<Match> getMatchesByPlayerName(String name, int pageNumber) throws SQLException {
        return matchesDAO.getMatchesByPlayerName(name, pageNumber);
    }

    @Override
    public boolean hasNextPage(String playerName, int pageNumber) throws SQLException {
        return matchesDAO.hasNextPage(playerName, pageNumber);
    }
    @Override
    public boolean hasNextPage(int pageNumber) throws SQLException {
        return matchesDAO.hasNextPage(pageNumber);
    }
}
