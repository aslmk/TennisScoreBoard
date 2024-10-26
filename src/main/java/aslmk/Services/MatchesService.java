package aslmk.Services;

import aslmk.Models.Match;

import java.sql.SQLException;
import java.util.List;

public interface MatchesService {
    List<Match> getAllMatches() throws SQLException;
    List<Match> getAllMatchesByPage(int page) throws SQLException;
    List<Match> getMatchesByPlayerId(int playerId) throws SQLException;
    boolean hasNextPage(int playerId, int pageNumber) throws SQLException;
    boolean hasNextPage(int pageNumber) throws SQLException;
}
