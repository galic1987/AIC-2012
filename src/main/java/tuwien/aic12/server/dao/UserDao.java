/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.aic12.server.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import tuwien.aic12.model.User;

/**
 *
 * @author vanjalee
 */
public class UserDao implements Dao<User> {

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
}
