package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface QueryService {

    public String search(
            @WebParam(name = "token") String token,
            @WebParam(name = "dateFrom") String dateFrom,
            @WebParam(name = "dateTo") String dateTo,
            @WebParam(name = "jobid") Long jobid);

    public String registerService(
            @WebParam(name = "token") String token);

    public String unregisterService(
            @WebParam(name = "token") String token,
            @WebParam(name = "jobid") Long jobid);
}
