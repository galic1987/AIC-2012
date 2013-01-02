package tuwien.aic12.server.service.impl;

import tuwien.aic12.model.Customer;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    private String CUSTOMER_NOT_FOUND = "Customer not found!";
    private String TOKEN_INVALID = "Token is invalid!";
    private String LOGOUT_SUCCESS = "Customer is now logged out!";
    CustomerDao customerDao = new CustomerDao();

    @Override
    public String registerCustomer(String username, String password, String company) {

        Customer customer = new Customer();
        customer.setPassword(username);
        customer.setUsername(password);
        customer.setCompany(company);
        customer = customerDao.create(customer);
        return "Hello " + customer.getUsername() + " ... customer saved with id : " + customer.getId();
    }

    @Override
    public String login(String customername, String password) {
        Customer customer = new Customer();
        customer.setUsername(customername);
        customer.setPassword(password);
        customer = customerDao.find(customer);
        if (customer != null) {
            System.out.print("Token :" + customer.getToken());
            return customer.getToken().toString();
        } else {
            return CUSTOMER_NOT_FOUND;
        }
    }

    @Override
    public String logout(String token) {
        Customer customer = customerDao.findCustomerByToken(token);
        if (customer != null) {
            return LOGOUT_SUCCESS;
        } else {
            return TOKEN_INVALID;
        }
    }
}
