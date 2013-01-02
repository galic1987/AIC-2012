package tuwien.aic12.server.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import tuwien.aic12.model.Customer;
import tuwien.aic12.model.Job;
import tuwien.aic12.model.Rating;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.dao.RatingDao;
import tuwien.aic12.server.service.MonitoringService;
import tuwien.aic12.server.twitter.TwitterService;

public class MonitoringServiceImpl implements MonitoringService {

    @Override
    public String analyse(String token, Long jobId) {
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.findCustomerByToken(token);
        
        return customer.getEmail();
    }

    @Override
    public String stop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
