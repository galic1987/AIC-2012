package tuwien.aic12.server.service.impl;

import tuwien.aic12.server.service.BillingService;

public class BillingServiceImpl implements BillingService {

	@Override	
	public String test(String testParam){
		return "Yeah Maaaaan! I agree with " + testParam;
	}

}
