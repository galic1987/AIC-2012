package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService//(name = "CustomerService", portName="CustomerServicePort")
public interface CustomerService {

    public String registerCustomer(@WebParam(name = "username") String username, @WebParam(name = "password") String password);

    public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password);

    public String logout(@WebParam(name = "token") String token);
}
