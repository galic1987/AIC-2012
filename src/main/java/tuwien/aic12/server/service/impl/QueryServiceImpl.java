package tuwien.aic12.server.service.impl;

import java.util.Date;
import java.util.List;
import tuwien.aic12.model.Customer;
import tuwien.aic12.model.Job;
import tuwien.aic12.model.Rating;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.dao.JobDao;
import tuwien.aic12.server.dao.RatingDao;
import tuwien.aic12.server.service.QueryService;

public class QueryServiceImpl implements QueryService {

    CustomerDao customerDao = new CustomerDao();
    JobDao jd = new JobDao();

    @Override
    public String search(String token, String dateFrom, String dateTo, Long jobId) {
        int counter_all = 0;
        double sum = 0;

        Customer customer = customerDao.findCustomerByToken(token);

        RatingDao rd = new RatingDao();
        List<Rating> list_rating = rd.findRatings(dateFrom, dateTo, customer.getId(), jobId);
        for (Rating rating : list_rating) {
            counter_all++;
            sum += rating.getRating();
        }
        if (counter_all == 0) {
            return "no Ratings found!";
        }
        return "" + sum / counter_all;
    }

    @Override
    public String registerService(String token) {
        Customer customer = customerDao.findCustomerByToken(token);
        if (customer != null) {
            Job job = new Job();
            Date startDate = new Date();
            job.setDateFrom(startDate.toString());
            job.setCustomer(customer);
            job.setRegistred(Boolean.TRUE);
            job = jd.create(job);
            return job.getId().toString();
        }
        return "No such user";
    }

    @Override
    public String unregisterService(String token, Long jobId) {
        Customer customer = customerDao.findCustomerByToken(token);
        if (customer != null) {
            Job job = jd.read(jobId);
            if (job != null) {
                Date stopDate = new Date();
                job.setDateTo(stopDate.toString());
                job.setRegistred(Boolean.FALSE);
                jd.update(job);
                return "Job unregistered successfully!";
            } else {
                return "Job not found!";
            }
        }
        return "No such user";

    }
}
