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
   protected DBManager() {
      // Exists only to defeat instantiation.
   }
   
   private static EntityManagerFactory entityManagerFactory;
   private static EntityManager entityManager;
   
   public static DBManager getInstance() {
      if(instance == null) {
         instance = new DBManager();
         entityManagerFactory = Persistence.createEntityManagerFactory("aic12");
         entityManager = entityManagerFactory.createEntityManager();
      }
      return instance;
   }
   
   public EntityManager getEntityManager(){
       return entityManager;
   }
    
}
