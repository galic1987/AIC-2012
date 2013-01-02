package tuwien.aic12.server.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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

    public ExecutorThread(String subject, String email, String reciever) {
        this.subject = subject;
        this.email = email;
        this.reciever = reciever;
    }

    public ExecutorThread(String subject, String from, String to, String email, String reciever) {
        this.subject = subject;
        this.from = from;
        this.to = to;
        this.email = email;
        this.reciever = reciever;
    }

    @Override
    public void run() {
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
        try {
            mailSender.postMail(reciever, email, emailContent, "Tweets Analysis Report");
        } catch (MessagingException ex) {
            Logger.getLogger(ExecutorThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.interrupt();
    }
}
