package de.bloodink;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Message-Driven Bean implementation class for: MailMDB
 */

@MessageDriven(mappedName = "jdbc/jmsQueue", activationConfig = { @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
/*
 * @ActivationConfigProperty(propertyName = "messageSelector", propertyValue =
 * "orderAmount > 1000")
 */})
public class MailMDB implements MessageListener {

    @Resource(name = "jdbc/javamail")
    private Session ms;

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage omsg = (ObjectMessage) message;

            // MimeMessage msg = (MimeMessage) omsg.getObject();
            String msg = (String) omsg.getObject();
            // Transport.send(msg);

            test();

            System.out.println("Mail received: " + msg.toString());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    private void test() {

        String content = "new user: franz mdb test";
        MimeMessage msg = new MimeMessage(ms);

        try {

            msg.setFrom(new InternetAddress("franz.mathauser@googlemail.com"));
            InternetAddress[] to = { new InternetAddress("info@blood-ink.de") };
            msg.setRecipients(RecipientType.TO, to);
            msg.setSubject("MDB Asyncmail");
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html");

            Transport.send(msg);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
