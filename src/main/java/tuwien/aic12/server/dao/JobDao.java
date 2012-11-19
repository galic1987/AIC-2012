/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.aic12.server.dao;

import javax.persistence.EntityManager;
import tuwien.aic12.model.Job;

/**
 *
 * @author Petar
 */
public class JobDao implements Dao<Job> {

    private EntityManager em;

    public JobDao(){
        em = DBManager.getInstance().getEntityManager();
    }

    @Override
    public Job create(Job t) {
        em.persist(t);
        return t;
    }

    @Override
    public Job update(Job t) {
        return em.merge(t);
    }

    @Override
    public boolean delete(Job t) {
        em.remove(t);
        return em.contains(t);
    }

    @Override
    public Job read(int id) {
        return em.find(Job.class, id);
    }
    
}
