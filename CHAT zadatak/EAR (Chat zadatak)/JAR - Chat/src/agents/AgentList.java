package agents;

import java.util.HashMap;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Singleton;

@Singleton
@LocalBean
@Remote(AgentListRemote.class)
public class AgentList implements AgentListRemote {

	HashMap<String, Agent> runningAgents;

	public AgentList() {
		runningAgents = new HashMap<>();
	}
	
	@Override
	public HashMap<String, Agent> getRunningAgents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRunningAgent(String key, Agent agent) {
		// TODO Auto-generated method stub

	}

}
