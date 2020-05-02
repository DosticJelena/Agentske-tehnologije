package messaging;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

@Stateless
@LocalBean
@Remote(MessageManagerRemote.class)
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
		try {
			defaultProducer.send(createTextMessage(msg));
		} catch (JMSException e) {
			e.printStackTrace();
		}
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
