package lookup;

import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import agentmanager.AgentManager;
import agentmanager.AgentManagerRemote;
import agents.Agent;
import agents.ChatAgent;
import agents.HostAgent;
import messaging.MessageManager;
import messaging.MessageManagerRemote;

public class JNDILookup {

	public static final String JNDIPathChat = "ejb:/EAR - Chat/JAR_-_Chat//";
	public static final String AgentManagerLookup = JNDIPathChat + AgentManager.class.getSimpleName() + "!"
			+ AgentManagerRemote.class.getName();
	public static final String MessageManagerLookup = JNDIPathChat + MessageManager.class.getSimpleName() + "!"
			+ MessageManagerRemote.class.getName();
	public static final String ChatAgentLookup = JNDIPathChat + ChatAgent.class.getSimpleName() + "!"
			+ Agent.class.getName() + "?stateful";
	public static final String HostAgentLookup = JNDIPathChat + HostAgent.class.getSimpleName() + "!"
			+ Agent.class.getName() + "?stateful";

	public static <T> T lookUp(String name, Class<T> c) {
		Map<String, String> env = System.getenv();
		env.forEach((k, v) -> System.out.println(k + ":" + v));
		
		T bean = null;
		try {
			Context context = new InitialContext();

			System.out.println("Looking up: " + name);
			bean = (T) context.lookup(name);

			context.close();

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}
}
