package agents;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

//@Stateful
@Remote(Agent.class)
public class HostAgent implements Agent {

	@EJB
	private AgentListRemote agents;
	
	private String agentId;
	
	@Override
	public String init() {
		agentId = "host";
		agents.addRunningAgent(agentId, this);
		return agentId;
	}

	@Override
	public void handleMessage(Message msg) {
		TextMessage tmsg = (TextMessage) msg;
		String receiver;
		String method;
		try {
			receiver = (String) tmsg.getObjectProperty("receiver");
			method = (String) tmsg.getObjectProperty("method");
			if (receiver.equals(agentId)) {
				System.out.println("[HOST AGENT] Method: " + method);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getAgentId() {
		return agentId;
	}

}
