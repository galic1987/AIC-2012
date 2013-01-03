package tuwien.aic12.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface BillingService {

    public String getBill(@WebParam(name = "token") String token);

    public String payRating(@WebParam(name = "ratingId") Long ratingId, @WebParam(name = "amount") Double amount);

    public String payBill(@WebParam(name = "token") String token, @WebParam(name = "amount") Double amount);
}
