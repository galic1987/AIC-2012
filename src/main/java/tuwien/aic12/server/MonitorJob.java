/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.aic12.server;

import java.util.Date;
import tuwien.aic12.model.Customer;
import tuwien.aic12.model.Job;
import tuwien.aic12.model.Rating;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.dao.RatingDao;
import tuwien.aic12.server.twitter.TwitterService;

/**
 *
 * @author Amin
 */
public class MonitorJob implements Runnable {

    private Job job;

    @Override
    public void run() {
        CustomerDao cd = new CustomerDao();
        Customer cust = cd.read(job.getCustid());

        TwitterService twitterService = new TwitterService();
        Double result;
        Date start = new Date();
        result = twitterService.getOpinionOf(cust.getCompany_name());
        Date end = new Date();
        // Evaluation usually lasts over one minute
        long duration = end.getTime() - start.getTime();

        RatingDao rd = new RatingDao();
        Rating rating = new Rating();
        rating.setCustomer(cust.getId());
        rating.setJob(job.getId());
        rating.setRating(result);
        rating.setFee(10);
        rating.setDuration(duration);
        rd.create(rating);
    }

    /**
     * @return the job
     */
    public Job getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Job job) {
        this.job = job;
    }
}
