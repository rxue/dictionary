package rx.backoffice;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.ResourceAdapter;

@MessageDriven(name = "DataExportQueueMDB", 
	activationConfig = {
		@ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "false"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/DataExportQueue"),
        //@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/DataExportQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "user", propertyValue = "jmsuser"),
        @ActivationConfigProperty(propertyName = "password", propertyValue = "jmspassword"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
    })
@ResourceAdapter("remote-artemis")
public class DataExportBean implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("First Message driven bean");
	}

}
