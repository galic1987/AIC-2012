package tuwien.aic12.server.service.impl;

import java.util.List;
import tuwien.aic12.model.Customer;
import tuwien.aic12.model.Rating;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.dao.RatingDao;
import tuwien.aic12.server.service.BillingService;

public class BillingServiceImpl implements BillingService {
    
    CustomerDao customerDao = new CustomerDao();

    @Override
    public double getBill(String token) {
        double price = 0.0;        
        Customer customer = customerDao.findCustomerByToken(token);
        RatingDao rd = new RatingDao();
        List<Rating> list_rating = rd.findRatingsByCustomer(customer.getId());
        for (Rating rating : list_rating) {
            price += rating.getFee();
            price += (rating.getDuration() * 0.01);
        }
        return price;
    }

    @Override
    public String payRating(Long ratingId) {
        RatingDao rd = new RatingDao();
        Rating rat = rd.findRatingById(ratingId);
        rat.setFee(0.0);
        rat.setDuration((long) 0.0);
        return "Rating payed!";
    }

    @Override
    public String payBill(String token) {
        Customer customer = customerDao.findCustomerByToken(token);
        RatingDao rd = new RatingDao();
        List<Rating> list_rating = rd.findRatingsByCustomer(customer.getId());
        for (Rating rating : list_rating) {
            rating.setFee(0.0);
            rating.setDuration((long) 0);
        }
        return "Bill payed!";
    }
}
