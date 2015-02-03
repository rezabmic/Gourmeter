package cz.cvut.fel.jee.gourmeter.jms;



import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.cvut.fel.jee.gourmeter.ejb.FacilitySessionBean;
import cz.cvut.fel.jee.gourmeter.helper.EmailMessage;
import cz.cvut.fel.jee.gourmeter.helper.EmailSender;

@MessageDriven(
		mappedName = "java:global/jms/myQueue",
		activationConfig = {
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
			    @ActivationConfigProperty(propertyName="destination", propertyValue="java:global/jms/myQueue")
		}
		
)
public class MessageReceiverAsync implements MessageListener {

	private static final Logger log = LoggerFactory
			.getLogger(FacilitySessionBean.class);
	
	@Inject
	EmailSender mailSender;
	
	@Override
    public void onMessage(Message message) {
        try {
            EmailMessage em = message.getBody(EmailMessage.class);
            
            this.log.info("Received message in MessageReceiverAsync: " + em);
            mailSender.sendMessage(em);
            
        } catch (JMSException ex) {
        	this.log.warn("Cannot retrieve message from JMS Queue");
        }
    }
}