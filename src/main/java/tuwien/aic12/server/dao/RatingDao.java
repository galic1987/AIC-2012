package tuwien.aic12.server.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    public Rating read(long id) {
        return em.find(Rating.class, id);
    }

    public List<Rating> findRatings(String dateFrom, String dateTo, long custId, long jobId) {
        Query q = em.createQuery("SELECT r FROM rating r WHERE r.ts >= '" + dateFrom + "' AND r.ts <= '" + dateTo + "' AND r.job= '" + jobId + "' AND r.customer='" + custId + "'");
        return q.getResultList();
    }

    public List<Rating> findRatingsByCustomer(long custId) {
        Query q = em.createQuery("SELECT r FROM rating r WHERE r.customer='" + custId + "'");
        return q.getResultList();
    }

    public Rating findRatingById(long ratingId) {
        Query q = em.createQuery("SELECT r FROM rating r WHERE r.id='" + ratingId + "'");
        return (Rating) q.getResultList().get(0);
    }
}
