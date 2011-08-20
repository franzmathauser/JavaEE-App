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
 * Message-Driven Bean implementation class for: MailMDB
 */

@MessageDriven(mappedName = "jdbc/jmsQueue", activationConfig = { @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
/*
 * @ActivationConfigProperty(propertyName = "messageSelector", propertyValue =
 * "orderAmount > 1000")
 */})
public class MailMDB implements MessageListener {

    @EJB
    MailEjb mailer;

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage omsg = (ObjectMessage) message;

            MailMessage mailMessage = (MailMessage) omsg.getObject();

            mailer.sendSyncMail(mailMessage);

            System.out.println("Mail received: " + mailMessage.toString());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
