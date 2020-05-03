package ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;



@Singleton
@ServerEndpoint("/ws/loggedIn")
@LocalBean
public class WSLoggedIn {

	static List<Session> sessions = new ArrayList<Session>();
	
	@OnOpen
	public void onOpen(Session session) {
		if (!sessions.contains(session)) {
			System.out.println("New session!!!");
			sessions.add(session);
		}
	}
	
	@OnMessage
	public void echoTest(String json) {
		try {
			for (Session s : sessions) {
				s.getBasicRemote().sendText(json);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	@OnClose
	public void close(Session session) {
		sessions.remove(session);
	}
	
	@OnError
	public void error(Session session, Throwable t) {
		sessions.remove(session);
		t.printStackTrace();
	}
}
