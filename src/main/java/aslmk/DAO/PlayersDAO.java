package aslmk.DAO;

import aslmk.Exceptions.PlayerAlreadyExistsException;
import aslmk.Exceptions.PlayerSaveFailedException;
import aslmk.Models.Player;
import aslmk.Utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.Optional;


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
    public Player getPlayerById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "FROM Player WHERE id = :id";
            Query<Player> query = session.createQuery(hql, Player.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (HibernateException e) {
            throw new PlayerSaveFailedException(e.getMessage());
        }
    }
    public void createPlayer(Player player) throws PlayerSaveFailedException {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
        } catch (HibernateException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new PlayerAlreadyExistsException();
            }
            throw new PlayerSaveFailedException("Failed to save player " + player.getName() + " : " + e.getMessage());
        }
    }
}
