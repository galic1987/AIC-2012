package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService//(name = "AnalyserService", portName = "AnalyserServicePort")
public interface AnalyserService {

    public String analyseFromTo(@WebParam(name = "subject") String subject,
            @WebParam(name = "from") String from,
            @WebParam(name = "to") String to);

    public String analyse(@WebParam(name = "subject") String subject);
}
