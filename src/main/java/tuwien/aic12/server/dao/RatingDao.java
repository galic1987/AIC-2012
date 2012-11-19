/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
        em.persist(t);
        return t;
    }

    @Override
    public Rating update(Rating t) {
        return em.merge(t);
    }

    @Override
    public boolean delete(Rating t) {
        em.remove(t);
        return em.contains(t);
    }

    @Override
    public Rating read(int id) {
        return em.find(Rating.class, id);
    }
    
}
