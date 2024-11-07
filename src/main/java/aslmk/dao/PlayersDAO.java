package aslmk.dao;

import aslmk.exceptions.PlayerAlreadyExistsException;
import aslmk.exceptions.PlayerSaveFailedException;
import aslmk.models.Player;
import aslmk.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;


public class PlayersDAO {
    public Player getPlayerByName(String name) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "FROM Player WHERE name = :name";
            Query<Player> query = session.createQuery(hql, Player.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        } catch (HibernateException e) {
            throw new PlayerSaveFailedException("Can't find player with name " + name);
        }
    }
    public void createPlayer(Player player) throws PlayerSaveFailedException, PlayerAlreadyExistsException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
        } catch (HibernateException e) {
            if (e.getCause() instanceof ConstraintViolationException || e instanceof ConstraintViolationException) {
                throw new PlayerAlreadyExistsException();
            }
            throw new PlayerSaveFailedException("Failed to save player " + player.getName() + " : " + e.getMessage());
        }
    }
}
