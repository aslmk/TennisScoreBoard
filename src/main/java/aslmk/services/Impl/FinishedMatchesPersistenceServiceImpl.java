package aslmk.services.Impl;

import aslmk.dao.MatchesDAO;
import aslmk.exceptions.MatchSaveFailedException;
import aslmk.models.Match;
import aslmk.services.FinishedMatchesPersistenceService;
import org.hibernate.HibernateException;

import java.util.List;

public class FinishedMatchesPersistenceServiceImpl implements FinishedMatchesPersistenceService {
    MatchesDAO matchesDAO = new MatchesDAO();
    @Override
    public void saveMatchToDatabase(Match match) throws MatchSaveFailedException {
        matchesDAO.saveMatchToDatabase(match);
    }
    @Override
    public List<Match> getMatchesByPage(int page) throws HibernateException {
        return matchesDAO.getMatchesByPage(page);
    }

    @Override
    public List<Match> getMatchesByPlayerName(String name, int pageNumber) throws HibernateException {
        return matchesDAO.getMatchesByPlayerName(name, pageNumber);
    }

    @Override
    public boolean hasNextPage(String playerName, int pageNumber) throws HibernateException {
        return matchesDAO.hasNextPage(playerName, pageNumber);
    }
    @Override
    public boolean hasNextPage(int pageNumber) throws HibernateException {
        return matchesDAO.hasNextPage(pageNumber);
    }
}
