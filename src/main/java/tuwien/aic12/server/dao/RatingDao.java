package tuwien.aic12.server.dao;

import javax.persistence.EntityManager;
import tuwien.aic12.model.Rating;

/**
 *
 * @author Petar
 */
public class RatingDao implements Dao<Rating> {

    private EntityManager em;

    public RatingDao() {
        em = DBManager.getInstance().getEntityManager();
    }

    @Override
    public Rating create(Rating t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        return t;
    }

    @Override
    public Rating update(Rating t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        return t;
    }

    @Override
    public boolean delete(Rating t) {
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
        return em.contains(t);
    }

    @Override
    public Rating read(int id) {
        return em.find(Rating.class, id);
    }
}
