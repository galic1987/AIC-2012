package tuwien.aic12.server.service.impl;

import tuwien.aic12.server.service.MonitoringService;

public class MonitoringServiceImpl implements MonitoringService{

	@Override
	public String test(String testParam) {
		return "Mother fucker! " + testParam;
	}

    @Override
    public String start() {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String stop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
