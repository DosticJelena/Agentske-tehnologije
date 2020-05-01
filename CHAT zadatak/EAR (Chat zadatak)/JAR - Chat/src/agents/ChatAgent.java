package agents;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.jms.Message;

//@Stateful
@Remote(Agent.class)
public class ChatAgent implements Agent {

	@EJB
	private AgentListRemote agents;
	
	private String agentId;
	
	@Override
	public String init() {
		agentId = "chat";
		agents.addRunningAgent(agentId, this);
		return agentId;
	}

	@Override
	public void handleMessage(Message message) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getAgentId() {
		return agentId;
	}

}
