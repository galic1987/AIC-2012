package tuwien.aic12.server.service.impl;

import tuwien.aic12.model.Customer;
import tuwien.aic12.model.Job;
import tuwien.aic12.model.Rating;
import tuwien.aic12.server.Constants;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.dao.JobDao;
import tuwien.aic12.server.service.BillingService;

public class BillingServiceImpl implements BillingService {

    CustomerDao customerDao = new CustomerDao();

    @Override
    public String getBill(String token) {
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
        return "Your Bill for " + numberOfJobsToPay + " finished and unpayed jobs is : " + price;
    }

    @Override
    public String payRating(Long jobId, Double amount) {
        JobDao jobDao = new JobDao();
        Job job = jobDao.read(jobId);
        if (job != null) {
            if (job.getJobStatus().equals(Job.JobStatus.FINISHED) && job.getJobPayedStatus().equals(Job.JobPayedStatus.UNPAYED)) {
                if (amount.compareTo(calculateJobBill(jobId)) >= 0) {
                    job.setJobPayedStatus(Job.JobPayedStatus.PAYED);
                    jobDao.update(job);
                    return "Single rating payed";
                } else {
                    return "Provided amount is too small.!";
                }
            } else {
                return "Rating not finished or already payed.";
            }
        } else {
            return "Unknown request";
        }
    }

    @Override
    public String payBill(String token, Double ammount) {
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

    private Double calculateJobBill(Long jobId) {
        Double price = null;
        JobDao jobDao = new JobDao();
        Job job = jobDao.read(jobId);
        if (job != null && job.getJobStatus().equals(Job.JobStatus.FINISHED) && job.getJobPayedStatus().equals(Job.JobPayedStatus.UNPAYED)) {
            job.setJobPayedStatus(Job.JobPayedStatus.PAYED);
            price += job.getRating().getFee();
            price += ((int) (job.getRating().getDuration() / 1000) / Constants.timeSlot) * Constants.timeSlotPrice;
        }
        return price;
    }
}
