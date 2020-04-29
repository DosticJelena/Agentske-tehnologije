package messagemanager;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.Topic;

@Singleton
@LocalBean
public class JMSFactory {

	private Connection connection;
	@Resource(lookup = "java:jboss/exported/jms/RemoteConnectionFactory")
	private ConnectionFactory connectionFactory;
	@Resource(lookup = "java:jboss/exported/jms/topic/publicTopic")
	private Topic defaultTopic;
	
	public Session getSession() {
		try {
			return connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
		} catch (JMSException ex) {
			throw new IllegalStateException(ex);
		}
	}

	public MessageProducer getProducer(Session session) {
		try {
			return session.createProducer(defaultTopic);
		} catch (JMSException ex) {
			throw new IllegalStateException(ex);
		}
	}

	public MessageConsumer getConsumer(Session session) {
		try {
			return session.createConsumer(defaultTopic);
		} catch (JMSException ex) {
			throw new IllegalStateException(ex);
		}
	}
}
