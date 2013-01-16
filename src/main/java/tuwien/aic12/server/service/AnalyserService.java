package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface AnalyserService {

    public String analyseFromTo(@WebParam(name = "subject") String subject,
            @WebParam(name = "dateFrom") String dateFrom,
            @WebParam(name = "dateTo") String dateTo,
            @WebParam(name = "token") String token);

    public String analyse(@WebParam(name = "subject") String subject,
            @WebParam(name = "token") String token);
}
