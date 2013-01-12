package tuwien.aic12.server;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tuwien.aic12.model.Customer;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.service.impl.AnalyserServiceImpl;
import tuwien.aic12.server.twitter.search.TwitterSearcher;
import twitter4j.Status;

/**
 *
 * @author vanjalee
 */
public class Test {

    public static void main(String[] args) {
        testCalculation();
    }

    private static void testCalculation(){
        AnalyserServiceImpl analyserServiceImpl = new AnalyserServiceImpl();
        analyserServiceImpl.analyse("muslim", "app5j226hah6oohjhnqmuam00c");
        
        // TwitterSearcher ts = new TwitterSearcher();
        // List<Status> tweets = ts.createTwitterQuerySinceUntil("jon jones","2013-01-01", "2013-01-11");
        // System.out.println(tweets.size());
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
