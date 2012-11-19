/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.aic12.server.dao;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import tuwien.aic12.model.User;

/**
 *
 * @author vanjalee
 */
public class UserDao implements Dao<User> {

    private SecureRandom random = new SecureRandom();
    private EntityManager em;

    public UserDao(){
        em = DBManager.getInstance().getEntityManager();
    }
    
    @Override
    public User create(User t) {
        em.persist(t);
        return t;
    }

    @Override
    public User update(User t) {
        return em.merge(t);
    }

    @Override
    public boolean delete(User t) {
        em.remove(t);
        return em.contains(t);
    }

    @Override
    public User read(int id) {
        return em.find(User.class, id);
    }
    
    public User find(User user) {
        Query q = em.createQuery("SELECT u FROM user u WHERE u.username = '" + user.getUsername() + "' and u.password = '" + user.getPassword() + "';");
        User usr = (User) q.getResultList().get(0);
        usr.setToken(nextSessionId());
        usr.setLastlogintime(new Timestamp(new java.util.Date().getTime()));
        this.update(usr);
        return usr;
    }
    
        public String nextSessionId()
      {
        return new BigInteger(130, random).toString(32);
      }
}
