package tuwien.aic12.server.service.impl;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import tuwien.aic12.model.Customer;
import tuwien.aic12.model.Job;
import tuwien.aic12.model.JobStatus;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.dao.JobDao;
import tuwien.aic12.server.notifier.MailSender;
import tuwien.aic12.server.twitter.TwitterService;
import tuwien.aic12.server.util.StringUtil;

/**
 *
 * @author vanjalee
 */
public class ExecutorThread extends Thread {

    private String subject;
    private String from;
    private String to;
    private String email;
    private String reciever;
    private TwitterService twitterService = new TwitterService();
    private MailSender mailSender = new MailSender();
    private StringUtil stringUtil = new StringUtil();
    private JobDao jobDao = new JobDao();
    private Customer customer;
    private Long jobId;
    private Job job;

    public ExecutorThread(String subject, String email, String reciever, Customer customer, Job job) {
        this.subject = subject;
        this.email = email;
        this.reciever = reciever;
        this.customer = customer;
        this.job = job;
    }

    public ExecutorThread(String subject, String from, String to, String email, String reciever, Customer customer, Job job) {
        this.subject = subject;
        this.from = from;
        this.to = to;
        this.email = email;
        this.reciever = reciever;
        this.customer = customer;
        this.job = job;
    }

    @Override
    public void run() {
        try {

            job.setJobStatus(JobStatus.RUNNING);

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
            job.setJobStatus(JobStatus.FINISHED);
            job.getRating().setRatingEnd(end);
            job.getRating().setDuration(end.getTime() - job.getRating().getRatingStart().getTime());

        } catch (MessagingException ex) {
            Logger.getLogger(ExecutorThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.interrupt();
    }
}
