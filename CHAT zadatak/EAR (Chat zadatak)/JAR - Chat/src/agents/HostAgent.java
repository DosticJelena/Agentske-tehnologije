package agents;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.jms.Message;

@Stateful
@Remote(Agent.class)
public class HostAgent implements Agent {

	@Override
	public String init() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleMessage(Message message) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getAgentId() {
		// TODO Auto-generated method stub
		return null;
	}

}
