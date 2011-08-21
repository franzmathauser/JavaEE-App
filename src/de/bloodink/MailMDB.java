package de.bloodink;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import de.bloodink.ejbs.MailEjb;

/**
 * Message-Driven Bean implementation class for: MailMDB This class listens on
 * the Queue. A mail is send if a MailMessage arrives in queue.
 */
@MessageDriven(mappedName = "jdbc/jmsQueue", activationConfig = { @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class MailMDB implements MessageListener {

    /**
     * Inject a MailEjb to send a synchronized JavaMail Session.
     */
    @EJB
    private MailEjb mailer;

    /**
     * Callback if a new MailMessage comes into Queue.
     * 
     * @param message
     *            quemessage
     */
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage omsg = (ObjectMessage) message;

            if (omsg instanceof MailMessage) {

                MailMessage mailMessage = (MailMessage) omsg.getObject();

                mailer.sendSyncMail(mailMessage);

                System.out.println("Mail received: " + mailMessage.toString());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
