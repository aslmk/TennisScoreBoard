package aslmk.DAO;

import aslmk.Models.Match;
import aslmk.Models.MatchScore;
import aslmk.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MatchesDAO {
    public List<Match> getAllMatches() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Match", Match.class).list();
        }
    }
    public void saveMatchToDatabase(Match match) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
        }
    }
}
