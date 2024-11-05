package aslmk.services;

import aslmk.dto.MatchDTO;
import aslmk.exceptions.MatchSaveFailedException;
import aslmk.models.Match;
import org.hibernate.HibernateException;

import java.util.List;

public interface FinishedMatchesPersistenceService {
    void saveMatchToDatabase(MatchDTO match) throws MatchSaveFailedException;
    List<Match> getMatchesByPage(int page) throws HibernateException;
    List<Match> getMatchesByPlayerName(String name, int pageNumber) throws HibernateException;
    boolean hasNextPage(int pageNumber) throws HibernateException;
    boolean hasNextPage(String playerName, int pageNumber) throws HibernateException;
}
