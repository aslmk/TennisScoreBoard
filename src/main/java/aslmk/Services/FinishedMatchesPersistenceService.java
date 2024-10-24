package aslmk.Services;

import aslmk.Exceptions.MatchSaveFailedException;
import aslmk.Models.Match;

import java.sql.SQLException;

public interface FinishedMatchesPersistenceService {
    void saveMatchToDatabase(Match match) throws MatchSaveFailedException;
}
