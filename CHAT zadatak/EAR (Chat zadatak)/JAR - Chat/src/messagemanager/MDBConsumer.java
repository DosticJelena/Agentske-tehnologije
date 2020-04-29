package messagemanager;

import javax.ejb.EJB;
import javax.jms.Message;
import javax.jms.MessageListener;

import agents.AgentListRemote;

public class MDBConsumer implements MessageListener {

	@EJB
	private AgentListRemote agents;
	
	public MDBConsumer() {

	}
	
	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		
	}

}
