package agents;

import javax.ejb.EJB;
import javax.jms.Message;

import data.UsersAndMessages;
import models.UserMessage;

public class ChatAgent implements Agent {

	private long userId; //agentId...
	@EJB
	private AgentListRemote agents;
	@EJB
	private UsersAndMessages data;
	
	@Override
	public String init() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleMessage(Message message) {
		UserMessage objMsg = (UserMessage) message;
		long receiver;
		long sender;
		String content;
		receiver = objMsg.getReceiverId();
		sender = objMsg.getSenderId();
		content = objMsg.getContent();
		System.out.println("==== MESSAGE ====");
		System.out.println("Receiver: " + receiver);
		System.out.println("Sender: " + sender);
		System.out.println("Content: " + content);
		System.out.println("=================");
		data.getMessages().add(objMsg);
	}

	@Override
	public String getAgentId() {
		// TODO Auto-generated method stub
		return null;
	}

}
