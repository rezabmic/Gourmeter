package cz.cvut.fel.jee.gourmeter.jms;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import cz.cvut.fel.jee.gourmeter.helper.EmailMessage;

@MessageDriven(
		mappedName = "java:global/jms/myQueue",
		activationConfig = {
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
			    @ActivationConfigProperty(propertyName="destination", propertyValue="java:global/jms/myQueue")
		}
		
)
public class MessageReceiverAsync implements MessageListener {
   
	@Override
    public void onMessage(Message message) {
        try {
            EmailMessage em = message.getBody(EmailMessage.class);
            System.out.println("Message received in MessageReceiverAsync: " + em);
        } catch (JMSException ex) {
            Logger.getLogger(MessageReceiverAsync.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}