package tuwien.aic12.server.service.impl;

import java.util.List;
import tuwien.aic12.model.Customer;
import tuwien.aic12.model.Rating;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.dao.RatingDao;
import tuwien.aic12.server.service.BillingService;

public class BillingServiceImpl implements BillingService {

	@Override	
	public String test(String testParam){
		return "Yeah Maaaaan! I agree with " + testParam;
	}

    @Override
    public double getBill(String token) {
        
        double price = 0.0;
        
        CustomerDao customerDao = new CustomerDao();
        
        Customer customer = new Customer();
        customer.setToken(token);
        customer = customerDao.findCustomerByToken(customer);
        
        RatingDao rd = new RatingDao();
        List<Rating> list_rating = rd.findRatingsByCustomer(customer.getId());
        for (Rating rating : list_rating) {
            price += rating.getFee();
            price += (rating.getDuration()*0.01);
        }
        
        return price;
    }

}
