package de.bloodink.listener;

import java.util.Date;

import javax.ejb.EJB;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.PostPersist;

import de.bloodink.ejbs.MailEjb;
import de.bloodink.entities.User;

public class UserEntityListener {

    @EJB
    MailEjb mailer;

    @PostPersist
    public void prePersist(User u) {

        String content = "new user: " + u.getName() + " => " + u.getPassword();
        MimeMessage msg = mailer.getInstance();

        try {

            InternetAddress[] to = { new InternetAddress("info@blood-ink.de") };
            msg.setRecipients(RecipientType.TO, to);
            msg.setSubject("New User");
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html");
            mailer.sendMail(msg);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
