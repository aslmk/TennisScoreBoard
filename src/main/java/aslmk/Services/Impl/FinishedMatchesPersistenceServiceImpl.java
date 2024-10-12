package aslmk.Services.Impl;

import aslmk.DAO.MatchesDAO;
import aslmk.Models.Match;
import aslmk.Services.FinishedMatchesPersistenceService;

public class FinishedMatchesPersistenceServiceImpl implements FinishedMatchesPersistenceService {
    MatchesDAO matchesDAO = new MatchesDAO();
    @Override
    public void saveMatchToDatabase(Match match) {
        matchesDAO.saveMatchToDatabase(match);
    }
}
