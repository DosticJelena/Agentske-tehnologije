package agents;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import data.UsersAndMessages;
import models.User;
import models.UserStatus;

//@Stateful
@Remote(Agent.class)
public class HostAgent implements Agent {

	@EJB
	private AgentListRemote agents;
	
	@EJB
	private UsersAndMessages data;
	
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
				
				if (method.equals("register")) { // ---- REGISTER ----
					
					User regUser = (User) tmsg.getObjectProperty("user");
					long maxId = 0;
					for (User u : data.getUsers()) {
						if (u.getId() > maxId) {
							maxId = u.getId();
						}
						if (u.getUsername().equals(regUser.getUsername())) {
							return;
						}
					}
					User newUser = new User(maxId + 1, regUser.getUsername(), regUser.getPassword());
					data.getUsers().add(newUser);
					
				} else if (method.equals("login")) { // ---- LOGIN ----
					
					User logUser = (User) tmsg.getObjectProperty("user");
					for (User u : data.getUsers()) {
						if (u.getUsername().equals(logUser.getUsername())) {
							if (!u.getPassword().equals(logUser.getPassword())) {
								return;
							}
							u.setLoggedIn(UserStatus.LOGGED_IN);
						}
					}
				}
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
