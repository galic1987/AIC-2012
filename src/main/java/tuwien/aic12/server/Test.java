package tuwien.aic12.server;

import tuwien.aic12.model.Customer;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.twitter.TwitterService;

/**
 *
 * @author vanjalee
 */
public class Test {

    public static void main(String[] args) {
        // insertUser();
        String subject = "Nick Diaz";
        
        TwitterService twitterService = new TwitterService();
        Double result = twitterService.getOpinionOf(subject);
        
        System.out.println("Twitter community opinion of : " + subject + ", is : " + result);
    }

    private static void insertUser() {
        Customer customer = new Customer();
        customer.setPassword("123");
        customer.setUsername("123");

        CustomerDao customerDao = new CustomerDao();
        customerDao.create(customer);
    }
}
