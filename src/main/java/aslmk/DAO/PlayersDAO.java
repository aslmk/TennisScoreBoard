package aslmk.DAO;

import aslmk.Models.Player;
import aslmk.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class PlayersDAO {
    public Player getPlayerByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Player player = null;

        try {
            transaction = session.beginTransaction();

            String hql = "FROM Player WHERE name = :name";
            Query<Player> query = session.createQuery(hql, Player.class);
            query.setParameter("name", name);
            player = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return player;
    }
    public Player getPlayerById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Player player = null;

        try {
            transaction = session.beginTransaction();

            String hql = "FROM Player WHERE id = :id";
            Query<Player> query = session.createQuery(hql, Player.class);
            query.setParameter("id", id);
            player = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return player;
    }
    public void save(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(player);
        tx1.commit();
        session.close();
    }
    public void createPlayer(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}
