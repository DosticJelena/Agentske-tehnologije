package messaging;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import messagemanager.AgentMessage;

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
	
	@PreDestroy
	public void preDestroy() {
		try {
			session.close();
		} catch (JMSException e) {
		}
	}
	
	@Override
	public void post(AgentMessage msg) {
		// TODO Auto-generated method stub

	}
	
	private Message createTextMessage(AgentMessage amsg) {
		Message msg = null ;
		try {
			msg = session.createTextMessage();
			for(String property : amsg.userArgs.keySet()) {
				msg.setObjectProperty(property, amsg.userArgs.get(property));
			}
			return msg;
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return msg;
		
		
	}

	@Override
	public Session getSession() {
		return factory.getSession();
	}

	@Override
	public MessageConsumer getConsumer() {
		return factory.getConsumer(session);
	}

}
