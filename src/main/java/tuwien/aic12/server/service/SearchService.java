package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="searchService")
public interface SearchService {

	public String test(@WebParam(name = "testParam") String testParam);
	
}
