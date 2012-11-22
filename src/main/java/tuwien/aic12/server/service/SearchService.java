package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="searchService")
public interface SearchService {

	public String test(@WebParam(name = "testParam") String testParam);
        
        public String search(
                            @WebParam(name = "searchString") String searchString,
                            @WebParam(name = "token") String token,
                            @WebParam(name = "dateFrom") String dateFrom,
                            @WebParam(name = "dateTo") String dateTo
                            );
}
