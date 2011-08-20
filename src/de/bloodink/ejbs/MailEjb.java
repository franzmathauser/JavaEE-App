package de.bloodink.ejbs;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

@Stateless
public class MailEjb {

    @Resource(mappedName = "jdbc/jmsConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jdbc/jmsQueue")
    private Queue queue;

    public boolean sendMail() {

        try {
            // msg.setFrom(new
            // InternetAddress("franz.mathauser@googlemail.com"));
            Connection connection = connectionFactory.createConnection();
            javax.jms.Session session = connection.createSession(false,
                    javax.jms.Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);

            // Sends an object message to the queue
            ObjectMessage message = session.createObjectMessage();
            message.setObject("Das ist ein TEST");
            // message.setFloatProperty("orderAmount", totalAmount);
            producer.send(message);
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
