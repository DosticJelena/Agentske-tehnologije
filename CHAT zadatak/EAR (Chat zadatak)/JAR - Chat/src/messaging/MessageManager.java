package messaging;

import javax.jms.MessageConsumer;
import javax.jms.Session;

public class MessageManager implements MessageManagerRemote {

	@Override
	public void post(AgentMessage msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public Session getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageConsumer getConsumer() {
		// TODO Auto-generated method stub
		return null;
	}

}