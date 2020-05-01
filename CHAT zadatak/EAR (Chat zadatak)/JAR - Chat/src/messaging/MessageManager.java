package messaging;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

public class MessageManager implements MessageManagerRemote {

	@EJB
	private JMSFactory factory;
	
	private Session session;
	private MessageProducer defaultProducer;

	@PostConstruct
	public void postConstruct() {
		session = factory.getSession();
		defaultProducer = factory.getProducer(session);
	}
	
	public MessageManager() {
		
	}
	
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
