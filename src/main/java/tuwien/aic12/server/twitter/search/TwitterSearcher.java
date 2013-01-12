package tuwien.aic12.server.twitter.search;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author bisanov
 */
public class TwitterSearcher {

    private Twitter twitter;

    public TwitterSearcher() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("ExTuf7o9OdqsFpvGvAmJ5A")
                .setOAuthConsumerSecret("y1s7G1Etr8XJEY2VoKddrZEVDqkVjSMUXeM7VGPBY")
                .setOAuthAccessToken("957131425-2sTC8XiYMkRHO837CmF3RRGje21FDQCV5c9epGFA")
                .setOAuthAccessTokenSecret("FaAXCJscW9mphqfccJyzwKrVYyPFG4ysSzn4OfukWc");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public List<Status> createTwitterQuery(String searchObject) {
        Query query = new Query(searchObject);
        query.setCount(100);
        List<Status> result = new ArrayList<>();
        try {
            result = twitter.search(query).getTweets();
            System.out.println("TwitterSearcher : Number of selected tweets : " + result.size());
        } catch (TwitterException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return result;
    }

    // Dates have to be formatted as : YYYY-MM-DD
    public List<Status> createTwitterQuerySinceUntil(String searchObject, String since, String until) {
        Query queryFrom = new Query(searchObject);
        Query queryTo = new Query(searchObject);
        
        queryFrom.setCount(100);
        queryFrom.setSince(since);
        
        queryTo.setCount(100);
        queryTo.setUntil(until);
        
        List<Status> resultFrom = new ArrayList<>();
        List<Status> resultTo = new ArrayList<>();
        try {
            resultFrom = twitter.search(queryFrom).getTweets();
            resultTo = twitter.search(queryTo).getTweets();
            resultFrom.addAll(resultTo);
            System.out.println("TwitterSearcher : Number of selected tweets : " + resultFrom.size());

        } catch (TwitterException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return resultFrom;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }
}
