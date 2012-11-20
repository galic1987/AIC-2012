package tuwien.aic12.server.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vanjalee
 */
public class DBManager {

    private static DBManager instance = null;
    private static EntityManagerFactory entityManagerFactory = null;
    private static EntityManager entityManager = null;

    protected DBManager() {
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
            entityManagerFactory = Persistence.createEntityManagerFactory("aic12");
            entityManager = entityManagerFactory.createEntityManager();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
