package tuwien.aic12.server.service.impl;

import java.util.Date;
import javax.jws.WebService;
import tuwien.aic12.model.Customer;
import tuwien.aic12.model.Job;
import tuwien.aic12.model.JobPayedStatus;
import tuwien.aic12.model.JobStatus;
import tuwien.aic12.model.Rating;
import tuwien.aic12.server.Constants;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.service.AnalyserService;

/**
 *
 * @author vanjalee
 */
@WebService(endpointInterface = "tuwien.aic12.server.service.AnalyserService")
public class AnalyserServiceImpl implements AnalyserService {

    private CustomerDao customerDao = new CustomerDao();

    @Override
    public String analyseFromTo(String subject, String from, String to, String token) {
        Customer customer = customerDao.findCustomerByToken(token);
        if (customer != null) {
            if (customer.getRegistred() == true) {
                if ((new Date().getTime() - customer.getRegisterTime().getTime()) <= customer.getRegisterDuration()) {

                    Job job = new Job();
                    job.setCustomer(customer);
                    job.setDateFrom(from);
                    job.setDateTo(to);
                    job.setJobPayedStatus(JobPayedStatus.UNPAYED);
                    job.setJobStatus(JobStatus.SCHEDULED);
                    job.setSubject(subject);
                    Rating rating = new Rating();
                    rating.setRatingStart(new Date());
                    rating.setFee(Constants.requestFee);
                    job.setRating(rating);
                    customer.getJobs().add(job);
                    customerDao.update(customer);

                    ExecutorThread executorThread = new ExecutorThread(subject, from, to, customer.getEmail(), customer.getUsername(), customer, job);
                    executorThread.start();
                    return "Your Results will be send to your email as soon as we are done with"
                            + " analysis. The details about the analysis, as well as the costs"
                            + ", will be provided along the analysis results.\nThanx for working"
                            + " with us :)";
                } else {
                    customer.setRegistred(false);
                    customer.setRegisterTime(null);
                    customer.setRegisterDuration(null);
                    customer.setToken(null);
                    customerDao.update(customer);
                    return "Your registration has expired. You will have to register again, and provide a payment for all of the unpayed jobs, if having any. Afterwards, you will be allowed the access to all the data again";
                }
            } else {
                return "You will have to register first " + customer.getUsername();
            }
        } else {
            return "Invalid request...";
        }
    }

    @Override
    public String analyse(String subject, String token) {
        Customer customer = customerDao.findCustomerByToken(token);
        if (customer != null) {
            if (customer.getRegistred() == true) {
                if ((new Date().getTime() - customer.getRegisterTime().getTime()) <= customer.getRegisterDuration()) {


                    Job job = new Job();
                    job.setCustomer(customer);
                    job.setJobPayedStatus(JobPayedStatus.UNPAYED);
                    job.setJobStatus(JobStatus.SCHEDULED);
                    job.setSubject(subject);
                    Rating rating = new Rating();
                    rating.setRatingStart(new Date());
                    rating.setFee(Constants.requestFee);                    
                    job.setRating(rating);
                    customer.getJobs().add(job);
                    customerDao.update(customer);

                    ExecutorThread executorThread = new ExecutorThread(subject, "vanja.bisanovic@gmail.com", "Vanja Lee", customer, job);
                    executorThread.start();
                    return "Your Results will be send to your email as soon as we are done with"
                            + " analysis. The details about the analysis, as well as the costs"
                            + ", will be provided along the analysis results.\nThanx for working"
                            + " with us :)";
                } else {
                    customer.setRegistred(false);
                    customer.setRegisterTime(null);
                    customer.setRegisterDuration(null);
                    customer.setToken(null);
                    customerDao.update(customer);
                    return "Your registration has expired. You will have to register again, and provide a payment for all of the unpayed jobs, if having any. Afterwards, you will be allowed the access to all the data again";
                }
            } else {
                return "You will have to register first " + customer.getUsername();
            }
        } else {
            return "Invalid request...";
        }
    }
}
