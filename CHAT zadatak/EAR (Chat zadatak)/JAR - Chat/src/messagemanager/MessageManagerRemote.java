package messagemanager;

import javax.ejb.Remote;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import models.UserMessage;

@Remote
public interface MessageManagerRemote {
	
	public void post(UserMessage msg);
	public Session getSession();
	public MessageConsumer getConsumer();
	
}
