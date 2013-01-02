package tuwien.aic12.server.notifier;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author vanjalee
 */
public class MailTester {

    public static void main(String[] args) {
        MailSender ms = new MailSender();
        try {
            ms.postMail("Bruce Lee", "vanja.bisanovic@gmail.com", "blah, blah, blah, blah", "Twitter Analysis Report");
        } catch (MessagingException ex) {
            Logger.getLogger(MailTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
