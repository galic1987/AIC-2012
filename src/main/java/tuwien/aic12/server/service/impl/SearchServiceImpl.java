package tuwien.aic12.server.service.impl;

import tuwien.aic12.server.service.SearchService;

public class SearchServiceImpl implements SearchService{

	@Override
	public String test(String testParam) {
		return "U rock Dude!!! " + testParam;
	}

}
