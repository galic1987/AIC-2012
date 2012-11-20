package tuwien.aic12.server;

import tuwien.aic12.model.Customer;
import tuwien.aic12.server.dao.CustomerDao;

/**
 *
 * @author vanjalee
 */
public class DaoTest {
    
    public static void main(String [] args) {        
        // insertUser();
    }
    
        
    private static void insertUser(){
    	Customer customer = new Customer();
    	customer.setPassword("123");
    	customer.setUsername("123");
    	
        CustomerDao customerDao = new CustomerDao();
        customerDao.create(customer);        
    }
    
}
