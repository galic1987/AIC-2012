/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.aic12.server.twitter;

import java.util.List;
import tuwien.aic12.server.twitter.search.TwitterSearcher;
import tuwien.aic12.server.twitter.semantics.TwitterSemantics;
import twitter4j.Status;

/**
 *
 * @author vanjalee
 */
public class TwitterService { // This Service will probably need to be asynchron, because the analysis lasts long. Leaving it undeclared as WebService for now.

    public Double getOpinionOf(String subject) {
        Double result = performSearch(false, subject, null, null);
        return result;
    }

    // Dates have to be formatted as : YYYY-MM-DD
    public Double getOpinionOfFromTo(String subject, String from, String to) {
        Double result = performSearch(true, subject, from, to);      
        return result;
    }

    private Double performSearch(boolean fromTo, String subject, String from, String to) {
        TwitterSearcher twitterSearcher = new TwitterSearcher();
        TwitterSemantics twitterSemantics = new TwitterSemantics();
        List<Status> toBeAnalysed;
        if (fromTo) {
            toBeAnalysed = twitterSearcher.createTwitterQuerySinceUntil(subject, from, to);
        } else {
            toBeAnalysed = twitterSearcher.createTwitterQuery(subject);
        }
        return twitterSemantics.analyse(toBeAnalysed);
    }
}
