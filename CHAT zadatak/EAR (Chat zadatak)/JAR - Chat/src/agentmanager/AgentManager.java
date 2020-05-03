package agentmanager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import agents.Agent;
import agents.AgentListRemote;
import lookup.JNDILookup;

@Stateless
@LocalBean
@Remote(AgentManagerRemote.class)
public class AgentManager implements AgentManagerRemote {

	@EJB
	private AgentListRemote agents;
	
	public AgentManager() {
		
	}
	
	@Override
	public String startAgent(String name) {
		Agent agent = (Agent) JNDILookup.lookUp(name, Agent.class);
		return agent.init();
	}

	@Override
	public Agent getAgentById(String agentId) {
		return agents.getRunningAgents().get(agentId);
	}

}
