package aslmk.Services;

import aslmk.Models.Match;

public interface FinishedMatchesPersistenceService {
    void saveMatchToDatabase(Match match);
}
