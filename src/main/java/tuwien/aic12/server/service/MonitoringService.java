package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService//(name = "MonitoringService", portName="MonitoringServicePort")
public interface MonitoringService {

    public String start();

    public String stop();
}
