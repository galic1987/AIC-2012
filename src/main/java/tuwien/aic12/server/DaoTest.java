package tuwien.aic12.server;

import java.util.List;
import tuwien.aic12.model.Customer;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.twitter.search.TwitterSearcher;
import tuwien.aic12.server.twitter.semantics.TwitterSemantics;
import twitter4j.Tweet;

/**
 *
 * @author vanjalee
 */
public class DaoTest {

    public static void main(String[] args) {
        // insertUser();
        String subject = "whore";
        TwitterSearcher twitterSearcher = new TwitterSearcher();
        List<Tweet> toBeAnalysed = twitterSearcher.createTwitterQuery(subject);
        TwitterSemantics twitterSemantics = new TwitterSemantics();
        Double result = twitterSemantics.analyse(toBeAnalysed);
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
