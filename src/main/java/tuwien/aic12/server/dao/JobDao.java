package tuwien.aic12.server.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import tuwien.aic12.model.Job;

/**
 *
 * @author Petar
 */
public class JobDao implements Dao<Job> {

    private EntityManager em;

    public JobDao() {
        em = DBManager.getInstance().getEntityManager();
    }

    @Override
    public Job create(Job t) throws Exception {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        return t;
    }

    @Override
    public Job update(Job t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        return t;
    }

    @Override
    public boolean delete(Job t) {
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
        return em.contains(t);
    }

    @Override
    public Job read(long id) {
        return em.find(Job.class, id);
    }

    public List<Job> readRegisteredJobs() {
        Query q = em.createQuery("SELECT j FROM job j WHERE j.registred = 'true';");
        List jobs = q.getResultList();
        return jobs;
    }
}
