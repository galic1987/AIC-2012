package tuwien.aic12.server.dao;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import tuwien.aic12.model.Customer;
import tuwien.aic12.server.Constants;

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
    public Customer create(Customer t) throws Exception {
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
        Constants.logger.log(Level.FINE, "QUERY : \"SELECT u FROM customer u WHERE u.username = '\" + customer.getUsername() + \"' and u.password = '\" + customer.getPassword() + \"';\"");
        Query q = em.createQuery("SELECT u FROM customer u WHERE u.username = '" + customer.getUsername() + "' and u.password = '" + customer.getPassword() + "';");
        Customer usr = null;
        if (!q.getResultList().isEmpty()) {
            usr = (Customer) q.getResultList().get(0);
            usr.setToken(nextSessionId());
            usr.setLastLoginTime(new Date());
            this.update(usr);
        }
        return usr;
    }

    public String find(String username, String password) {
        Query q = em.createQuery("SELECT u FROM customer u WHERE u.username = '" + username + "';");
        if (!q.getResultList().isEmpty()) {
            return "Username already in use";
        } else {
            return "";
        }
    }

    public Customer findCustomerByToken(String token) {
        System.out.println("token to find:" + token);
        Query q = em.createQuery("SELECT u FROM customer u WHERE u.token = '" + token + "'");
        Customer usr = null;
        if (!q.getResultList().isEmpty()) {
            usr = (Customer) q.getResultList().get(0);
        }
        return usr;
    }

     public Customer findCustomerByCompany(String company) {
        Query q = em.createQuery("SELECT u FROM customer u WHERE u.company = '" + company + "'");
        Customer usr = null;
        if (!q.getResultList().isEmpty()) {
            usr = (Customer) q.getResultList().get(0);
        }
        return usr;
    }
    
    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}
