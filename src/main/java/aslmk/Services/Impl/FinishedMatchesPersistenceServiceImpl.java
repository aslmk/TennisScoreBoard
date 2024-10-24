package aslmk.Services.Impl;

import aslmk.DAO.MatchesDAO;
import aslmk.Exceptions.MatchSaveFailedException;
import aslmk.Models.Match;
import aslmk.Services.FinishedMatchesPersistenceService;

import java.sql.SQLException;

public class FinishedMatchesPersistenceServiceImpl implements FinishedMatchesPersistenceService {
    MatchesDAO matchesDAO = new MatchesDAO();
    @Override
    public void saveMatchToDatabase(Match match) throws MatchSaveFailedException {
        matchesDAO.saveMatchToDatabase(match);
    }
}
