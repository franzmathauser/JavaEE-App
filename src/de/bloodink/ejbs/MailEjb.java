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

/**
 * MailEjb takes care of sending Emails. It's possible to send Synchronious and
 * Asynchronious mails
 * 
 * @author Franz Mathauser
 */
@Stateless
public class MailEjb {

    /**
     * Inject jms connectionFactory.
     */
    @Resource(mappedName = "jdbc/jmsConnectionFactory")
    private ConnectionFactory connectionFactory;

    /**
     * Inject jms queue.
     */
    @Resource(mappedName = "jdbc/jmsQueue")
    private Queue queue;

    /**
     * Inject javamail ressource.
     */
    @Resource(name = "jdbc/javamail")
    private Session ms;

    /**
     * Sends a MailMessage to a jms queue, which takes care of message delivery.
     * 
     * @param mailMessage
     *            mail information
     * @return send status
     */
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

    /**
     * Sends a MailMessage in a synchronious way.
     * 
     * @param mailMessage
     *            mail information
     * @return send status
     */
    public boolean sendSyncMail(MailMessage mailMessage) {

        MimeMessage msg = createMimeMessage(mailMessage);

        try {
            Transport.send(msg);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * Transforms a MailMessage to a MimeMessage, which can be send with
     * javamail.
     * 
     * @param mailMessage
     *            mail information
     * @return converted MimeMessage
     */
    private MimeMessage createMimeMessage(MailMessage mailMessage) {
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
