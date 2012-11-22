package tuwien.aic12.server.dao;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import tuwien.aic12.model.Customer;

/**
 *
 * @author vanjalee
 */
public class CustomerDao implements Dao<Customer> {

    private SecureRandom random = new SecureRandom();
    private EntityManager em;

    public CustomerDao() {
        em = DBManager.getInstance().getEntityManager();
    }

    @Override
    public Customer create(Customer t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        return t;
    }

    @Override
    public Customer update(Customer t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        return t;
    }

    @Override
    public boolean delete(Customer t) {
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
        return em.contains(t);
    }

    @Override
    public Customer read(long id) {
        return em.find(Customer.class, id);
    }

    public Customer find(Customer customer) {
        Query q = em.createQuery("SELECT u FROM customer u WHERE u.username = '" + customer.getUsername() + "' and u.password = '" + customer.getPassword() + "';");
        Customer usr = null;
        if (!q.getResultList().isEmpty()) {
            usr = (Customer) q.getResultList().get(0);
            usr.setToken(nextSessionId());
            usr.setLastlogintime(new Timestamp(new java.util.Date().getTime()));
            this.update(usr);
        }
        return usr;
    }

    public Customer findCustomerByToken(Customer customer) {
        System.out.println("token to find:" + customer.getToken());
        Query q = em.createQuery("SELECT u FROM customer u WHERE u.token = '" + customer.getToken() + "'");
        Customer usr = null;
        if (!q.getResultList().isEmpty()) {
            usr = (Customer) q.getResultList().get(0);
            usr.setToken("");
            this.update(usr);
        }
        return usr;
    }

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}
