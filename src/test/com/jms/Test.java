package com.jms;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by dev on 16-8-17.
 */
public class Test {
    public static void main(String[] args) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61616");
        Connection conn;
        Session session;
        try {
            conn = factory.createConnection();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = new ActiveMQQueue("spitter.queue");
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();

            message.setText("fdergtyj");
            producer.send(message);

            System.out.println(1);
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }
}
