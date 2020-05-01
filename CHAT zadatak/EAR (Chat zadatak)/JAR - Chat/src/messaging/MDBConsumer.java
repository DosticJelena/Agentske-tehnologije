package messaging;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import agents.Agent;
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
	public void onMessage(Message message) {
		
		String receiver;
		try {
			receiver = (String) message.getObjectProperty("receiver");
			Agent agent = (Agent) agents.getRunningAgents().get(receiver);
			agent.handleMessage(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
