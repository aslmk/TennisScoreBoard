package aslmk.dao;

import aslmk.exceptions.MatchSaveFailedException;
import aslmk.models.Match;
import aslmk.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MatchesDAO {
    private static final int pageSize = 10;

    public void saveMatchToDatabase(Match match) throws MatchSaveFailedException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
        } catch (HibernateException e) {
            throw new MatchSaveFailedException("Can't save current match.");
        }
    }
    public List<Match> getMatchesByPage(int pageNumber) throws HibernateException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            int offset = (pageNumber - 1) * 10;
            List<Match> matches = session.createQuery("FROM Match", Match.class)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .list();
            return matches;
        }
    }
    public List<Match> getMatchesByPlayerName(String playerName, int pageNumber) throws HibernateException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            int offset = (pageNumber - 1) * 10;
            String hql = "FROM Match WHERE player1.name LIKE :name OR player2.name LIKE :name";
            Query<Match> query = session.createQuery(hql, Match.class);
            query.setParameter("name", '%' + playerName + '%');

            query.setFirstResult(offset);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }
    public boolean hasNextPage(String playerName, int pageNumber) throws HibernateException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(m.id) FROM Match m WHERE m.player1.name LIKE :name OR m.player2.name LIKE :name";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("name", '%' + playerName + '%');
            long totalMatches = query.uniqueResult();
            return totalMatches > pageSize*pageNumber;
        }
    }
    public boolean hasNextPage(int pageNumber) throws HibernateException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(m.id) FROM Match m";
            Query<Long> query = session.createQuery(hql, Long.class);
            long totalMatches = query.uniqueResult();
            return totalMatches > pageSize*pageNumber;
        }
    }
}
