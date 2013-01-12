package tuwien.aic12.server.service.impl;

import java.util.Date;
import javax.jws.WebService;
import javax.mail.MessagingException;
import tuwien.aic12.model.Customer;
import tuwien.aic12.model.Job;
import tuwien.aic12.model.Rating;
import tuwien.aic12.server.Constants;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.dao.JobDao;
import tuwien.aic12.server.notifier.MailSender;
import tuwien.aic12.server.service.AnalyserService;
import tuwien.aic12.server.twitter.TwitterService;
import tuwien.aic12.server.util.StringUtil;

/**
 *
 * @author vanjalee
 */
@WebService(endpointInterface = "tuwien.aic12.server.service.AnalyserService")
public class AnalyserServiceImpl implements AnalyserService {

    private CustomerDao customerDao = new CustomerDao();
    private JobDao jobDao = new JobDao();

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
                    job.setJobPayedStatus(Job.JobPayedStatus.UNPAYED);
                    job.setJobStatus(Job.JobStatus.SCHEDULED);
                    job.setSubject(subject);
                    Rating rating = new Rating();
                    rating.setRatingStart(new Date());
                    rating.setFee(Constants.requestFeeFromTo);
                    job.setRating(rating);
                    job = jobDao.update(job);
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
                    job.setJobPayedStatus(Job.JobPayedStatus.UNPAYED);
                    job.setJobStatus(Job.JobStatus.SCHEDULED);
                    job.setSubject(subject);
                    Rating rating = new Rating();
                    rating.setRatingStart(new Date());
                    rating.setFee(Constants.requestFee);
                    job.setRating(rating);
                    job = jobDao.update(job);
                    customer.getJobs().add(job);
                    customerDao.update(customer);

                    ExecutorThread executorThread = new ExecutorThread(subject, customer.getEmail(), customer.getUsername(), customer, job);
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

    public class ExecutorThread extends Thread {

        private String subject;
        private String from;
        private String to;
        private String email;
        private String reciever;
        private Job job;

        public ExecutorThread(String subject, String email, String reciever, Customer customer, Job job) {
            this.subject = subject;
            this.email = email;
            this.reciever = reciever;
            this.job = job;
        }

        public ExecutorThread(String subject, String from, String to, String email, String reciever, Customer customer, Job job) {
            this.subject = subject;
            this.from = from;
            this.to = to;
            this.email = email;
            this.reciever = reciever;
            this.job = job;
        }

        @Override
        public void run() {
            TwitterService twitterService = new TwitterService();
            MailSender mailSender = new MailSender();
            StringUtil stringUtil = new StringUtil();
            JobDao jobDao = new JobDao();
            try {
                job.setJobStatus(Job.JobStatus.RUNNING);
                jobDao.update(job);
                Double result;
                String emailContent;
                if (stringUtil.isNotEmpty(from) || stringUtil.isNotEmpty(to)) {
                    result = twitterService.getOpinionOfFromTo(subject, from, to);
                    if (!stringUtil.isNotEmpty(from)) {
                        from = "some time ago";
                    }
                    if (!stringUtil.isNotEmpty(to)) {
                        to = "today";
                    }
                    emailContent = "Twitter community opinion of : " + subject + ", in time period since " + from + " until " + to + ", is : " + result;
                } else {
                    result = twitterService.getOpinionOf(subject);
                    emailContent = "Twitter community opinion of : " + subject + ", is : " + result;
                }
                mailSender.postMail(reciever, email, emailContent, "Tweets Analysis Report");
                Date end = new Date();
                job.setJobStatus(Job.JobStatus.FINISHED);
                job.getRating().setRatingEnd(end);
                job.getRating().setDuration(end.getTime() - job.getRating().getRatingStart().getTime());
                job.getRating().setRating(result);
                Double price = job.getRating().getFee();
                price += ((int) (job.getRating().getDuration() / 1000) / Constants.timeSlot) * Constants.timeSlotPrice;
                job.setPrice(price);
                jobDao.update(job);
            } catch (MessagingException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
            this.interrupt();
        }
    }
}
