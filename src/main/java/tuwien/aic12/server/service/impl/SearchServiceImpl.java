package tuwien.aic12.server.service.impl;

import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.service.SearchService;

public class SearchServiceImpl implements SearchService{

        CustomerDao customerDao = new CustomerDao();
	@Override
	public String test(String testParam) {
		return "U rock Dude!!! " + testParam;
	}

    @Override
    public String search(String searchString, String token, String dateFrom, String dateTo) {
                
        return "";
    }

}
