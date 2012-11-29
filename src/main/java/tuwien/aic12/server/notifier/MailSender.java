package tuwien.aic12.server.notifier;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

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
                        return new PasswordAuthentication("vanja.bisanovic", "VANJAasdfghjkl");
                    }
                });
        try {
            Message messageToBeSent = new MimeMessage(session);
            messageToBeSent.setFrom(new InternetAddress("vanja.bisanovic@gmail.com"));
            messageToBeSent.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recieverAddress));
            messageToBeSent.setSubject(subject);
            messageToBeSent.setText("Dear " + recieverName + ","
                    + "\n\n" + emailContent);
            Transport.send(messageToBeSent);
            System.out.println("MailSender : Mail sent to " + recieverAddress);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
