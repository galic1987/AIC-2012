package tuwien.aic12.server.service.impl;

import tuwien.aic12.server.service.SentimentService;
import tuwien.aic12.dto.JobDTO;
public class SentimentServiceImpl implements SentimentService{

	@Override
	public String startAnaylsis(JobDTO job) {
		return "Here comes sentiment analysis! " + job.getId();
	}

}
