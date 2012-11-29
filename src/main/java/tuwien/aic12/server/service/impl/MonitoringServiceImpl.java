package tuwien.aic12.server.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import tuwien.aic12.model.Customer;
import tuwien.aic12.model.Rating;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.dao.RatingDao;
import tuwien.aic12.server.service.MonitoringService;
import tuwien.aic12.server.twitter.TwitterService;

public class MonitoringServiceImpl implements MonitoringService {

    @Override
    public String analyse(long customerid, long jobid) {
        System.out.println("Starting analyse customerid: " + customerid + ", Job id: " + jobid);
        CustomerDao cd = new CustomerDao();
        Customer cust = cd.read(customerid);
        
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
        rating.setJob(jobid);
        rating.setRating(result);
        rating.setFee(10);
        rating.setDuration(duration);
        rating.setTs(new Timestamp(new java.util.Date().getTime()));
        rd.create(rating);
        return "Rating added for job :" + jobid;
    }

    @Override
    public String stop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
