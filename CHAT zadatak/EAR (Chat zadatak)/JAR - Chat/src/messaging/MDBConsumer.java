package messaging;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import agents.Agent;
import agents.AgentListRemote;
import ws.WSLoggedIn;
import ws.WSRegistered;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/topic/publicTopic") })
public class MDBConsumer implements MessageListener {

	@EJB
	private AgentListRemote agents;
	
	@EJB
	private WSRegistered wsR;
	
	@EJB
	private WSLoggedIn wsL;
	
	public MDBConsumer() {
		
	}
	
	@Override
	public void onMessage(Message message) {
		
		String receiver;
		try {
			receiver = (String) message.getObjectProperty("receiver");
			if (receiver.equals("ws")) {
				if (((String)message.getObjectProperty("method")).equals("registered")) {
					wsR.echoTest((String)message.getObjectProperty("users"));
				} else if (((String)message.getObjectProperty("method")).equals("loggedIn")) {
					wsL.echoTest((String)message.getObjectProperty("users"));
				} else if (((String)message.getObjectProperty("method")).equals("messages")) {
					//wsM.echoTest((String)message.getObjectProperty("messages"));
				}
			} else {
				Agent agent = (Agent) agents.getRunningAgents().get(receiver);
				agent.handleMessage(message);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
