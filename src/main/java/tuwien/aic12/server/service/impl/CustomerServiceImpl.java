package tuwien.aic12.server.service.impl;

import javax.jws.WebService;
import tuwien.aic12.model.Customer;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.dao.DBManager;
import tuwien.aic12.server.service.CustomerService;

@WebService(endpointInterface = "tuwien.aic12.server.service.CustomerService")
public class CustomerServiceImpl implements CustomerService {

    private String CUSTOMER_NOT_FOUND = "Customer not found!";
    private String TOKEN_INVALID = "Token is invalid!";
    private String LOGOUT_SUCCESS = "Customer is now logged out!";
    CustomerDao customerDao = new CustomerDao();

    @Override
    public String test(String testParam) {
        return "U rock Dude!!! " + testParam;
    }

    @Override
    public String registerCustomer(String username, String password) {

        Customer customer = new Customer();
        customer.setPassword(username);
        customer.setUsername(password);
        DBManager.getInstance().getEntityManager().getTransaction().begin();
        customer = customerDao.create(customer);
        DBManager.getInstance().getEntityManager().getTransaction().commit();

        return "Hello " + customer.getUsername() + " ... customer saved with id : " + customer.getId();
    }

    @Override
    public String login(String customername, String password) {
        Customer customer = new Customer();
        customer.setUsername(customername);
        customer.setPassword(password);
        DBManager.getInstance().getEntityManager().getTransaction().begin();
        customer = customerDao.find(customer);
        DBManager.getInstance().getEntityManager().getTransaction().commit();

        if (customer != null) {
            System.out.print("Token :" + customer.getToken());
            return customer.getToken().toString();
        } else {
            return CUSTOMER_NOT_FOUND;
        }
    }

    @Override
    public String logout(String token) {
        Customer customer = new Customer();
        customer.setToken(token);
        DBManager.getInstance().getEntityManager().getTransaction().begin();
        customer = customerDao.findCustomerByToken(customer);
        DBManager.getInstance().getEntityManager().getTransaction().commit();

        if (customer != null) {
            return LOGOUT_SUCCESS;
        } else {
            return TOKEN_INVALID;
        }
    }
}
