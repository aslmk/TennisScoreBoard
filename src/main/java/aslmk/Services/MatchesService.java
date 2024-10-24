package aslmk.Services;

import aslmk.Models.Match;

import java.sql.SQLException;
import java.util.List;

public interface MatchesService {
    List<Match> getAllMatches() throws SQLException;
}
