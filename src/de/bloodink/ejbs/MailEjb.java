package de.bloodink.ejbs;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailEjb {

    @Resource(name = "jdbc/javamail")
    private Session ms;

    public MimeMessage getInstance() {
        return new MimeMessage(ms);
    }

    public boolean sendMail(MimeMessage msg) {

        try {
            msg.setFrom(new InternetAddress("franz.mathauser@googlemail.com"));
            javax.mail.Transport.send(msg);

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
