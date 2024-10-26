package aslmk.DAO;

import aslmk.Models.Match;
import aslmk.Models.MatchScore;
import aslmk.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class MatchesDAO {
    private static final int pageSize = 10;

    public List<Match> getAllMatches() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            List<Match> matches = session.createQuery("FROM Match", Match.class).list();
            return matches;
        }
    }
    public void saveMatchToDatabase(Match match) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
        }
    }
    public List<Match> getAllMatchesByPage(int pageNumber) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            int offset = (pageNumber - 1) * 10;
            List<Match> matches = session.createQuery("FROM Match", Match.class).setFirstResult(offset).setMaxResults(pageSize).list();
            return matches;
        }
    }
}
