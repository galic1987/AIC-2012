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
    public String search(String token, String dateFrom, String dateTo, Long jobid) {
        int counter_all = 0;
        int counter_one = 0;
        int counter_zero = 0;
        int res = 2;

        Customer customer = new Customer();
        customer.setToken(token);
        customer = customerDao.findCustomerByToken(customer);

        RatingDao rd = new RatingDao();
        List<Rating> list_rating = rd.findRatings(dateFrom, dateTo, customer.getId(), jobid);
        for (Rating rating : list_rating) {
            counter_all++;
            if (rating.getRating() == 0) {
                counter_zero++;
            } else if (rating.getRating() == 1) {
                counter_one++;
            }
        }


        if (counter_all == 0) {
            return "no Ratings";
        }


        /*res = Math.max(counter_one, counter_zero);
         if (res == counter_one) {
         res = 1;
         }
         else if (res == counter_zero) {
         res = 0;
         }
         else {
         res = 2;
         }
         return ""+res;*/
        //should return arithmetic average
        return "" + counter_one / counter_all;
    }

    @Override
    public String registerService(String token) {
        Customer customer = new Customer();
        customer.setToken(token);
        customer = customerDao.findCustomerByToken(customer);

        if (customer != null) {
            Job job = new Job();
            Date startDate = new Date();
            job.setDateFrom(startDate.toString());
            job.setCustid(customer.getId());
            job.setRegistred(Boolean.TRUE);
            job.setInterval((double) 0);

            jd.create(job);
            return "Job registered successfully!";

        }
        return "No such user";
    }

    @Override
    public String unregisterService(String token, Long jobid) {
        Customer customer = new Customer();
        customer.setToken(token);
        customer = customerDao.findCustomerByToken(customer);


        if (customer != null) {

            Job job = new Job();
            job = jd.read(jobid);
            Date stopDate = new Date();
            job.setDateTo(stopDate.toString());
            job.setCustid(customer.getId());
            job.setInterval((double) 0);
            job.setRegistred(Boolean.FALSE);
            jd.update(job);
            return "Job unregistered successfully!";

        }
        return "No such user";

    }
}
