package tuwien.aic12.server.service.impl;

import javax.jws.WebService;
import tuwien.aic12.server.service.AnalyserService;

/**
 *
 * @author vanjalee
 */
@WebService(endpointInterface = "tuwien.aic12.server.service.AnalyserService")
public class AnalyserServiceImpl implements AnalyserService {

    @Override
    public String analyseFromTo(String subject, String from, String to) {
        ExecutorThread executorThread = new ExecutorThread(subject, from, to, "vanja.bisanovic@gmail.com", "Vanja Lee");
        executorThread.start();
        return "Your Results will be send to your email as soon as we are done with"
                + " analysis. The details about the analysis, as well as the costs"
                + ", will be provided along the analysis results.\nThanx for working"
                + " with us :)";
    }

    @Override
    public String analyse(String subject) {
        ExecutorThread executorThread = new ExecutorThread(subject, "vanja.bisanovic@gmail.com", "Vanja Lee");
        executorThread.start();
        return "Your Results will be send to your email as soon as we are done with"
                + " analysis. The details about the analysis, as well as the costs"
                + ", will be provided along the analysis results.\nThanx for working"
                + " with us :)";
    }
}
