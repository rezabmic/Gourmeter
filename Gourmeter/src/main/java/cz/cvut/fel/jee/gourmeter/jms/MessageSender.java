package cz.cvut.fel.jee.gourmeter.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import cz.cvut.fel.jee.gourmeter.helper.EmailMessage;

@Stateless
public class MessageSender {

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    JMSContext context;
    
    @Resource(mappedName="java:global/jms/myQueue")
    Queue queue;

    public void sendMessage(String message) {
        context.createProducer().send(queue, message);
    }
    
    public void sendMessage(EmailMessage message) {
        context.createProducer().send(queue, message);
    }
}