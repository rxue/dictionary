package rx.backoffice;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.ResourceAdapter;

import javax.ejb.ActivationConfigProperty;

@MessageDriven(name = "DataExportQueueMDB", 
activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/DataExportQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
@ResourceAdapter("remote-artemis")
public class BackofficeMDB implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("First Message driven bean");
	}

}
