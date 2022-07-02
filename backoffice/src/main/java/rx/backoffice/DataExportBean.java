package rx.backoffice;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName="xx")
public class DataExportBean implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("First Message driven bean");
	}

}
