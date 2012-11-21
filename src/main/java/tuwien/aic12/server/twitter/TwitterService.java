/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.aic12.server.twitter;

import java.util.Date;
import java.util.List;
import tuwien.aic12.server.twitter.search.TwitterSearcher;
import tuwien.aic12.server.twitter.semantics.TwitterSemantics;
import twitter4j.Tweet;

/**
 *
 * @author vanjalee
 */
public class TwitterService { // This Service will probably need to be asynchron, because the analysis lasts long. Leaving it undeclared as WebService for now.

    public Double getOpinionOf(String subject) {

        Date start = new Date();
        Double result = performSearch(false, subject, null, null);
        Date end = new Date();

        // Evaluation usually lasts over one minute
        long duration = end.getTime() - start.getTime();

        // Here some DAO needs to save this duration
        // in order to convert it into 'time-slots'
        // for our Bill

        return result;
    }

    // Dates have to be formatted as : YYYY-MM-DD
    public Double getOpinionOfFromTo(String subject, String from, String to) {

        Date start = new Date();
        Double result = performSearch(true, subject, from, to);
        Date end = new Date();

        // Evaluation usually lasts over one minute
        long duration = end.getTime() - start.getTime();

        // Here some DAO needs to save this duration
        // in order to convert it into 'time-slots'
        // for our Bill

        return result;
    }

    private Double performSearch(boolean fromTo, String subject, String from, String to) {
        TwitterSearcher twitterSearcher = new TwitterSearcher();
        TwitterSemantics twitterSemantics = new TwitterSemantics();
        List<Tweet> toBeAnalysed;
        if (fromTo) {
            toBeAnalysed = twitterSearcher.createTwitterQuerySinceUntil(subject, from, to);
        } else {
            toBeAnalysed = twitterSearcher.createTwitterQuery(subject);
        }
        return twitterSemantics.analyse(toBeAnalysed);
    }
}
