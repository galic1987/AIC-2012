package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface BillingService {

    public String getBill(@WebParam(name = "token") String token);
    
    public String getSingleJobBill(@WebParam(name = "jobId") Long jobId, 
            @WebParam(name = "token") String token);

    public String payRating(@WebParam(name = "jobId") Long jobId, 
            @WebParam(name = "amount") Double amount);

    public String payBill(@WebParam(name = "amount") Double amount, 
            @WebParam(name = "token") String token);
}
