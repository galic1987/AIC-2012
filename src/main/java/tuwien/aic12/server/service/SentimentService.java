package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import tuwien.aic12.model.Customer;

@WebService(name="sentimentService")
public interface SentimentService {

	public String startAnaylsis(@WebParam(name = "companyName") String companyName);
        
        
	
}
