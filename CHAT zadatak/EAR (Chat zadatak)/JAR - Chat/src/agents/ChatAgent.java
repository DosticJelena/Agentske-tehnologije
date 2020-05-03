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
import models.UserMessage;
import models.UserStatus;

@Stateful
@LocalBean
@Remote(Agent.class)
public class ChatAgent implements Agent {

	private static final long serialVersionUID = 1L;
	
	protected DataRemote data() {
		return (DataRemote)JNDILookup.lookUp(JNDILookup.DataLookup, UsersAndMessages.class);
	}
	
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
	public void handleMessage(Message msg) {
		ObjectMessage tmsg = (ObjectMessage) msg;
		String receiver;
		String method;
		try {
			receiver = (String) tmsg.getObjectProperty("receiver");
			method = (String) tmsg.getObjectProperty("method");
			if (receiver.equals(agentId)) {
				System.out.println("[CHAT AGENT] Method: " + method);
				
				if (method.equals("all")) { // ---- ALL ----
					
					AgentMessage amsg = (AgentMessage) tmsg.getObject();
					UserMessage msgg = (UserMessage) amsg.userArgs.get("userMessage");
					for (int i=0;i<data().getUsers().size();i++) {
						if (data().getUsers().get(i).getLoggedIn().equals(UserStatus.LOGGED_IN)) {
							long rec = data().getUsers().get(i).getId();
							data().getMessages().add(new UserMessage(msgg.getSubject(), msgg.getContent(), msgg.getSenderId(), rec));
						}
					}
					
				} else if (method.equals("one")) { // ---- ONE ----
					
					AgentMessage amsg = (AgentMessage) tmsg.getObject();
					UserMessage msgg = (UserMessage) amsg.userArgs.get("userMessage");
					msgg.setId();
					data().getMessages().add(new UserMessage(msgg.getSubject(), msgg.getContent(), msgg.getSenderId(), msgg.getReceiverId()));
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
