package com.ligeng.service.jms.impl;

import com.ligeng.entity.Spittle;
import com.ligeng.service.jms.IAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by dev on 16-8-17.
 */
@Service
public class AlertServiceImpl implements IAlertService{
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendSpitterAlert(final Spittle spittle) {
        jmsTemplate.send("spitter.alert.queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(spittle);
            }
        });
    }
}
