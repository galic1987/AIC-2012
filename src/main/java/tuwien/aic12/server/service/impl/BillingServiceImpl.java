package tuwien.aic12.server.service.impl;

import javax.xml.ws.BindingType;
import tuwien.aic12.model.Customer;
import tuwien.aic12.model.Job;
import tuwien.aic12.model.Rating;
import tuwien.aic12.server.Constants;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.dao.JobDao;
import tuwien.aic12.server.service.BillingService;

@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class BillingServiceImpl implements BillingService {

    CustomerDao customerDao = new CustomerDao();

    @Override
    public String getBill(String token) {
        Double price = 0.0;
        Customer customer = customerDao.findCustomerByToken(token);
        int numberOfJobsToPay = 0;
        if (customer != null) {
            if (customer.getJobs().size() > 0) {
                for (Job job : customer.getJobs()) {
                    if (job.getJobPayedStatus().equals(Job.JobPayedStatus.UNPAYED) && job.getJobStatus().equals(Job.JobStatus.FINISHED)) {
                        numberOfJobsToPay++;
                        Rating rating = job.getRating();
                        price += rating.getFee();
                        price += ((int) (rating.getDuration() / 1000) / Constants.timeSlot) * Constants.timeSlotPrice;
                    }
                }
            }
            return "Your Bill for " + numberOfJobsToPay + " finished and unpayed jobs is : " + price;
        }
        return "Invalid request...";
    }

    @Override
    public String payRating(Long jobId, Double amount) {
        System.out.println("Paying for job : jobId = " + jobId + " : amount = " + amount);
        JobDao jobDao = new JobDao();
        Job job = jobDao.read(jobId);
        if (job != null) {
            if (job.getJobStatus().equals(Job.JobStatus.FINISHED) && job.getJobPayedStatus().equals(Job.JobPayedStatus.UNPAYED)) {
                Double realPrice = jobDao.calculateJobBill(jobId);
                if (amount.compareTo(realPrice) >= 0) {
                    job.setJobPayedStatus(Job.JobPayedStatus.PAYED);
                    jobDao.update(job);
                    return "Single rating payed";
                } else {
                    return "Provided amount is too small.";
                }
            } else {
                return "Rating not finished or already payed.";
            }
        } else {
            return "Unknown request";
        }
    }

    @Override
    public String getSingleJobBill(Long jobId, String token) {
        Customer customer = customerDao.findCustomerByToken(token);
        JobDao jobDao = new JobDao();
        if (customer != null) {
            boolean jobFound = false;
            if (customer.getJobs().size() > 0) {
                for (Job job : customer.getJobs()) {
                    if (job.getId().equals(jobId)) {
                        jobFound = true;
                    }
                }
            }
            if (jobFound) {
                Double bill = jobDao.calculateJobBill(jobId);
                return "Bill for job : " + jobId + " : " + bill;
            }
            return "Bill payed!";

        } else {
            return "Unknown request.";
        }
    }

    @Override
    public String payBill(Double ammount, String token) {
        Customer customer = customerDao.findCustomerByToken(token);
        if (customer != null) {
            if (ammount.compareTo(calculateBill(token)) >= 0) {
                if (customer.getJobs().size() > 0) {
                    for (Job job : customer.getJobs()) {
                        if (job.getJobPayedStatus().equals(Job.JobPayedStatus.UNPAYED) && job.getJobStatus().equals(Job.JobStatus.FINISHED)) {
                            job.setJobPayedStatus(Job.JobPayedStatus.PAYED);
                        }
                    }
                }
                customerDao.update(customer);
                return "Bill payed!";
            } else {
                return "Provided amount is too small.!";
            }
        } else {
            return "Unknown request.";
        }
    }

    private Double calculateBill(String token) {
        Double price = 0.0;
        Customer customer = customerDao.findCustomerByToken(token);
        int numberOfJobsToPay = 0;
        if (customer.getJobs().size() > 0) {
            for (Job job : customer.getJobs()) {
                if (job.getJobPayedStatus().equals(Job.JobPayedStatus.UNPAYED) && job.getJobStatus().equals(Job.JobStatus.FINISHED)) {
                    numberOfJobsToPay++;
                    Rating rating = job.getRating();
                    price += rating.getFee();
                    price += ((int) (rating.getDuration() / 1000) / Constants.timeSlot) * Constants.timeSlotPrice;
                }
            }
        }
        return price;
    }
}
