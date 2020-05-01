package agentmanager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import agents.Agent;
import agents.AgentListRemote;

@Stateless
@LocalBean
public class AgentManager implements AgentManagerRemote {

	@EJB
	private AgentListRemote agents;
	
	public AgentManager() {
		
	}
	
	@Override
	public String startAgent(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Agent getAgentById(String agentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
