//package cz.cvut.fel.jee.gourmeter.jms;
//
//import javax.annotation.Resource;
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.jms.JMSContext;
//import javax.jms.JMSException;
//import javax.jms.Queue;
//
//import cz.cvut.fel.jee.gourmeter.helper.EmailMessage;
//
//@Stateless
//public class MessageReceiverSync {
//
//    @Inject
//    private JMSContext context;
//    
//    @Resource(mappedName="java:global/jms/myQueue")
//    Queue myQueue;
//
//    public String receiveMessage() {
//    	EmailMessage message = null;
//		try {
//			message = context.createConsumer(myQueue).receive().getBody(EmailMessage.class);
//		} catch (JMSException e) {
//			e.printStackTrace();
//		}
//        return "Received " + message;
//    }
//}
