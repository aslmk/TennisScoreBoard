package aslmk.DAO;

import aslmk.Models.Match;
import aslmk.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MatchesDAO {
    private static final int pageSize = 10;

    public void saveMatchToDatabase(Match match) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
        }
    }
    public List<Match> getMatchesByPage(int pageNumber) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            int offset = (pageNumber - 1) * 10;
            List<Match> matches = session.createQuery("FROM Match", Match.class).setFirstResult(offset).setMaxResults(pageSize).list();
            return matches;
        }
    }
    public List<Match> getMatchesByPlayerId(int playerId) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "FROM Match WHERE player1.id = :id OR player2.id = :id";
            Query<Match> query = session.createQuery(hql, Match.class);
            query.setParameter("id", playerId);
            List<Match> matches = query.list();
            return matches;
        }
    }
    public boolean hasNextPage(int playerId, int pageNumber) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(m.id) FROM Match m WHERE m.player1.id = :id OR m.player2.id = :id";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("id", playerId);
            long totalMatches = query.uniqueResult();
            return totalMatches > pageSize*pageNumber;
        }
    }
    public boolean hasNextPage(int pageNumber) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(m.id) FROM Match m";
            Query<Long> query = session.createQuery(hql, Long.class);
            long totalMatches = query.uniqueResult();
            return totalMatches > pageSize*pageNumber;
        }
    }
}
