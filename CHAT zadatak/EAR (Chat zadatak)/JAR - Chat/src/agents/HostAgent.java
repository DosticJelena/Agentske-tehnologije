package agents;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import data.DataRemote;
import data.UsersAndMessages;
import lookup.JNDILookup;
import messaging.AgentMessage;
import models.User;
import models.UserStatus;

@Stateful
@LocalBean
@Remote(Agent.class)
public class HostAgent implements Agent {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private AgentListRemote agents;
	
	protected DataRemote data() {
		return (DataRemote)JNDILookup.lookUp(JNDILookup.DataLookup, UsersAndMessages.class);
	}
	
	private String agentId;
	
	@Override
	public String init() {
		agentId = "host";
		agents.addRunningAgent(agentId, this);
		return agentId;
	}

	@Override
	public void handleMessage(Message msg) {
		ObjectMessage tmsg = (ObjectMessage) msg;
		String receiver;
		String method;
		try {
			receiver = (String) tmsg.getObjectProperty("receiver");
			method = (String) tmsg.getObjectProperty("method");
			if (receiver.equals(agentId)) {
				System.out.println("[HOST AGENT] Method: " + method);
				
				if (method.equals("register")) { // ---- REGISTER ----
					
					AgentMessage amsg = (AgentMessage) tmsg.getObject();
					User regUser = (User) amsg.userArgs.get("user");
					long maxId = 0;
					for (User u : data().getUsers()) {
						if (u.getId() > maxId) {
							maxId = u.getId();
						}
						if (u.getUsername().equals(regUser.getUsername())) {
							return;
						}
					}
					User newUser = new User(maxId + 1, regUser.getUsername(), regUser.getPassword());
					data().getUsers().add(newUser);
					
				} else if (method.equals("login")) { // ---- LOGIN ----
					
					AgentMessage amsg = (AgentMessage) tmsg.getObject();
					User logUser = (User) amsg.userArgs.get("user");
					for (User u : data().getUsers()) {
						if (u.getUsername().equals(logUser.getUsername())) {
							if (!u.getPassword().equals(logUser.getPassword())) {
								return;
							}
							u.setLoggedIn(UserStatus.LOGGED_IN);
						}
					}
				} else if (method.equals("logout")) { // ---- LOG OUT ----
					
					long userId = (long) tmsg.getObjectProperty("userId");
					for (User u : data().getUsers()) {
						if (u.getId() == userId) {
							u.setLoggedIn(UserStatus.NOT_LOGGED_IN);
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
