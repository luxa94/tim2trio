package rs.isa.mrs.trio.iceipice.services;

import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.BaseUser;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Nina on 23-May-16.
 */
@Service
public class InviteFriendService {

    public void sendMail(BaseUser user, String email) {

        final String username = "iceipice.trio@gmail.com";
        final String password = "fabuloso";
        final String subject = "Poziv prijatelja da se registruje";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        final String messageText = String.format("Dobar dan, Vaš prijatelj " + user.getName() + " " + user.getSurname() + " Vas je pozvao da se pridruzite portalu Iće&Piće kako bi zajedno mogli da obogatite svoje gurmansko iskustvo u restoranima širom Srbije. \nPridružite se klikom na link ispod.\n http://localhost:8080/#/register?friendId=" + user.getId());

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(messageText);

            Transport.send(message);
            System.out.println("Message sent!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
