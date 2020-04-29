package messagemanager;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import agents.AgentListRemote;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/topic/publicTopic") })
public class MDBConsumer implements MessageListener {

	@EJB
	private AgentListRemote agents;
	
	public MDBConsumer() {

	}
	
	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		
	}

}
