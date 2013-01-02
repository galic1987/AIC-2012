package tuwien.aic12.server.twitter.search;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Query;
import twitter4j.Tweet;
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
                .setOAuthAccessToken("957131425-AT67THn0WMhoZQt3IGqLKGSwcoDk8PLc3e8aZ56t")
                .setOAuthAccessTokenSecret("QOFG4cP3SE22VrfqB9DtnxhZ5GE2eYhFO2XGsO3igw");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public List<Tweet> createTwitterQuery(String searchObject) {
        Query query = new Query(searchObject);
        List<Tweet> result = new ArrayList<Tweet>();
        try {
            result = twitter.search(query).getTweets();
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Dates have to be formatted as : YYYY-MM-DD
    public List<Tweet> createTwitterQuerySinceUntil(String searchObject, String since, String until) {
        Query query = new Query(searchObject);
        query.setSince(since);
        query.setUntil(until);
        List<Tweet> result = new ArrayList<Tweet>();
        try {
            result = twitter.search(query).getTweets();
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }
}
