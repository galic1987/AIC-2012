package tuwien.aic12.server.service.impl;

import tuwien.aic12.server.service.SentimentService;

public class SentimentServiceImpl implements SentimentService{

	@Override
	public String startAnaylsis(String testParam) {
		return "Here comes sentiment analysis! " + testParam;
	}

}
