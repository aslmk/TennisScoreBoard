package aslmk.DAO;

import aslmk.Exceptions.PlayerSaveFailedException;
import aslmk.Models.Player;
import aslmk.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class PlayersDAO {
    public Player getPlayerByName(String name) {
        Player player;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Player WHERE name = :name";
            Query<Player> query = session.createQuery(hql, Player.class);
            query.setParameter("name", name);
            player = query.uniqueResult();
            transaction.commit();
        }

        return player;
    }
    public Player getPlayerById(int id) {
        Player player;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Player WHERE id = :id";
            Query<Player> query = session.createQuery(hql, Player.class);
            query.setParameter("id", id);
            player = query.uniqueResult();
            transaction.commit();
        }
        return player;
    }
    public void createPlayer(Player player) throws PlayerSaveFailedException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
        }
    }
}
