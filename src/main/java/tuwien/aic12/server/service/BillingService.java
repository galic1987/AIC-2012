package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService//(name = "BillingService", portName = "BillingServicePort")
public interface BillingService {

    public double getBill(@WebParam(name = "token") String token);

    public String payRating(@WebParam(name = "ratingId") Long ratingId);

    public String payBill(@WebParam(name = "token") String token);
}
