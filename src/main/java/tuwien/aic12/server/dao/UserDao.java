package tuwien.aic12.server.dao;

import javax.persistence.EntityManager;
import tuwien.aic12.model.User;

/**
 *
 * @author vanjalee
 */
public class UserDao implements Dao<User> {

    private EntityManager em;

    public UserDao() {
        em = DBManager.getInstance().getEntityManager();
    }

    @Override
    public User create(User t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        return t;
    }

    @Override
    public User update(User t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        return t;
    }

    @Override
    public boolean delete(User t) {
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
        return em.contains(t);
    }

    @Override
    public User read(int id) {
        return em.find(User.class, id);
    }
}
