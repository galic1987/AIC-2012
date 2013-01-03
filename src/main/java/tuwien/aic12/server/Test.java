package tuwien.aic12.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import tuwien.aic12.model.Customer;
import tuwien.aic12.server.dao.CustomerDao;

/**
 *
 * @author vanjalee
 */
public class Test {

    public static void main(String[] args) {
        insertUser();
    }

    private static void insertUser() {
        Customer customer = new Customer();
        customer.setPassword("test 2");
        customer.setUsername("test 2");

        CustomerDao customerDao = new CustomerDao();
        try {
            customerDao.create(customer);
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
