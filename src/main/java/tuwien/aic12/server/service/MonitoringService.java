package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface MonitoringService {

    public String analyse(@WebParam(name = "token") String token, @WebParam(name = "jobId") Long jobId);

    public String stop();
}
