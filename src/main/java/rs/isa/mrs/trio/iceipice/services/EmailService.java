package rs.isa.mrs.trio.iceipice.services;

import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.BaseUser;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by nikolalukic on 4/19/16.
 */
@Service
public class EmailService {

    public void sendMail(BaseUser user) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        final String username = "iceipice.trio@gmail.com";
        final String password = "fabuloso";
        final String subject = "Verifikacija lozinke";

        final String messageText = String.format("Uspešno ste se ulogovali na http://localhost:8080/#/authenticate/%d. \nMolimo Vas da klikom na link iznad potvrdite svoju lozinku.", user.getId());

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject(subject);
            message.setText(messageText);
            Transport.send(message);

            System.out.println("Message sent!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
