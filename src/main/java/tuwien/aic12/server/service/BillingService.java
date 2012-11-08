package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="billingService")
public interface BillingService {

	public String test(@WebParam(name = "testParam") String testParam);
	
}
