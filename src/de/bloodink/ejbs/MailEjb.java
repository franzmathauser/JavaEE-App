package de.bloodink.ejbs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import de.bloodink.MailMessage;

@Stateless
public class MailEjb {

    @Resource(mappedName = "jdbc/jmsConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jdbc/jmsQueue")
    private Queue queue;

    @Resource(name = "jdbc/javamail")
    private Session ms;

    public boolean sendAsyncMail(MailMessage mailMessage) {

        try {

            Connection connection = connectionFactory.createConnection();
            javax.jms.Session session = connection.createSession(false,
                    javax.jms.Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);

            // Sends an object message to the queue
            ObjectMessage message = session.createObjectMessage();

            message.setObject(mailMessage);

            producer.send(message);
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void sendSyncMail(MailMessage mailMessage) {

        MimeMessage msg = convertMailMessageToMimeMessage(mailMessage);

        try {
            Transport.send(msg);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private MimeMessage convertMailMessageToMimeMessage(MailMessage mailMessage) {
        MimeMessage msg = new MimeMessage(ms);
        try {
            List<InternetAddress> to = new ArrayList<InternetAddress>();
            for (String recipient : mailMessage.getRecipients()) {
                to.add(new InternetAddress(recipient));
            }

            msg.setRecipients(RecipientType.TO,
                    to.toArray(new InternetAddress[0]));

            msg.setSubject(mailMessage.getSubject());
            msg.setSentDate(new Date());
            msg.setContent(mailMessage.getContent(), "text/html");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return msg;

    }
}
