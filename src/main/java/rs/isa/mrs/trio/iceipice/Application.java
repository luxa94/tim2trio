package rs.isa.mrs.trio.iceipice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Scanner;


@SpringBootApplication
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Scanner scanner = new Scanner(System.in);

        //	System.out.println("Username for authentication: ");
        //	final String username = scanner.nextLine();
        final String username = "iceipice.trio@gmail.com";

        //	System.out.println("Password: ");
        //	final String password = scanner.nextLine();
        final String password = "fabuloso";

        //	System.out.println("From: ");
        //	final String fromEmailAddress = scanner.nextLine();
        final String fromEmailAddress = "iceipice.trio@gmail.com";

        System.out.println("To: ");
        final String toEmailAddress = scanner.nextLine();

        //	System.out.println("Subject: ");
        //	final String subject = scanner.nextLine();
        final String subject = "Verifikacija lozinke";

        //	System.out.println("Message: ");
        //	final String messageText = scanner.nextLine();
        final String messageText = "Uspešno ste se ulogovali na localhost:8080. Molimo Vas da klikom na link ispod potvrdite svoju lozinku.";

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);

            }
        } );



        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmailAddress));
            message.setRecipients(Message.RecipientType.TO,  InternetAddress.parse(toEmailAddress));
            message.setSubject(subject);
            message.setText(messageText);
            Transport.send(message);

            System.out.println("Message sent!");

        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

}
