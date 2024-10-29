package aslmk.Services;

import aslmk.Exceptions.MatchSaveFailedException;
import aslmk.Models.Match;

import java.sql.SQLException;
import java.util.List;

public interface FinishedMatchesPersistenceService {
    void saveMatchToDatabase(Match match) throws MatchSaveFailedException;
    List<Match> getMatchesByPage(int page) throws SQLException;
    List<Match> getMatchesByPlayerName(String name, int pageNumber) throws SQLException;
    boolean hasNextPage(int pageNumber) throws SQLException;
    boolean hasNextPage(String playerName, int pageNumber) throws SQLException;
}
