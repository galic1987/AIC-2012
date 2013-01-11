package tuwien.aic12.server.notifier;

import java.util.*;
import java.util.logging.Level;
import javax.mail.*;
import javax.mail.internet.*;
import tuwien.aic12.server.Constants;

/**
 *
 * @author vanjalee
 */
public class MailSender {

    public void postMail(String recieverName, String recieverAddress, String emailContent, String subject) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("nikola.nepostojeci", "zaq1xsw2cde3vfr4");
                    }
                });
        try {
            Message messageToBeSent = new MimeMessage(session);
            messageToBeSent.setFrom(new InternetAddress("nikola.nepostojeci@gmail.com"));
            messageToBeSent.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recieverAddress));
            messageToBeSent.setSubject(subject);
            messageToBeSent.setText("Dear " + recieverName + ","
                    + "\n\n" + emailContent);
            Transport.send(messageToBeSent);
            Constants.logger.log(Level.FINE, "MailSender : Mail sent to {0}", recieverAddress);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
