package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import tuwien.aic12.model.Customer;

@WebService(name="billingService")
public interface BillingService {

	public String test(@WebParam(name = "testParam") String testParam);
        
        public double getBill(@WebParam(name = "token")String token);
	
}
