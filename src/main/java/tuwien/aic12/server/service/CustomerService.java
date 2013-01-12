package tuwien.aic12.server.service;

import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;
import tuwien.aic12.dto.JobDTO;

@WebService
public interface CustomerService {

    public String registerCustomerInitial(
            @WebParam(name = "email") String email,
            @WebParam(name = "username") String username, 
            @WebParam(name = "password") String password, 
            @WebParam(name = "company") String company, 
            @WebParam(name = "registerDuration") Long registerDuration);

    public String registerCustomer(
            @WebParam(name = "company") String company, 
            @WebParam(name = "registerDuration") Long registerDuration);
    
    public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password);

    public String logout(@WebParam(name = "token") String token);
    
    public List<JobDTO> getJobs(@WebParam(name = "token") String token);
}
