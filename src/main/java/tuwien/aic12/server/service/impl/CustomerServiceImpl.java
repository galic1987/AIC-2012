package tuwien.aic12.server.service.impl;

import java.util.Date;
import java.util.logging.Level;
import tuwien.aic12.model.Customer;
import tuwien.aic12.server.Constants;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    private String CUSTOMER_NOT_FOUND = "Customer not found!";
    private String TOKEN_INVALID = "Token is invalid!";
    private String LOGOUT_SUCCESS = "Customer is now logged out!";
    CustomerDao customerDao = new CustomerDao();

    @Override
    public String login(String username, String password) {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer = customerDao.find(customer);
        if (customer != null) {
            Constants.logger.log(Level.FINE, "User : {0}, with token : {1}, just logged in.", new Object[]{customer.getId(), customer.getToken()});
            return customer.getToken().toString();
        } else {
            return CUSTOMER_NOT_FOUND;
        }
    }

    @Override
    public String logout(String token) {
        Customer customer = customerDao.findCustomerByToken(token);
        if (customer != null) {
            Constants.logger.log(Level.FINE, "{0} logged out", customer.getId());
            return LOGOUT_SUCCESS;
        } else {
            return TOKEN_INVALID;
        }
    }
    
    @Override
    public String registerCustomer(String company, Long registerDuration) {
        Customer customer = customerDao.findCustomerByCompany(company);
        if (customer != null) {
            customer.setToken(customerDao.nextSessionId());
            customer.setRegisterTime(new Date());
            customer.setRegisterDuration(registerDuration);
            try {
                customer = customerDao.update(customer);
                Constants.logger.log(Level.FINE, customer.getUsername() + " registered.");
                return "Register was successful. Please log in to continue...";
            } catch (Exception ex) {
                Constants.logger.log(Level.FINE, ex.getLocalizedMessage());
                return "Invalid Register Data. Please fill out all the required fields.";
            }
        } else {
            return "Company not found. Please do the initial register first.";
        }
    }

    @Override
    public String registerCustomerInitial(String email, String username, String password, String company, Long registerDuration) {
        String errorInfo = customerDao.find(username, password);
        if (errorInfo.length() == 0) {
            Customer customer = new Customer();
            customer.setPassword(password);
            customer.setUsername(username);
            customer.setCompany(company);
            customer.setEmail(email);
            customer.setToken(customerDao.nextSessionId());
            customer.setRegisterTime(new Date());
            customer.setRegisterDuration(registerDuration);
            customer.setRegistred(Boolean.TRUE);
            try {
                customer = customerDao.create(customer);
                Constants.logger.log(Level.FINE, customer.getUsername() + " registered.");
                return "Register was successful. Please log in to continue...";
            } catch (Exception ex) {
                Constants.logger.log(Level.FINE, ex.getLocalizedMessage());
                return "Invalid Register Data. Please fill out all the required fields.";
            }
        } else {
            return errorInfo;
        }
    }
}
