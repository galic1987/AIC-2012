package tuwien.aic12.server.service.impl;

import tuwien.aic12.server.service.MonitoringService;

public class MonitoringServiceImpl implements MonitoringService{

	@Override
	public String test(String testParam) {
		return "Mother fucker! " + testParam;
	}

}
