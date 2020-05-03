package lookup;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import agentmanager.AgentManager;
import agentmanager.AgentManagerRemote;
import agents.Agent;
import agents.ChatAgent;
import agents.HostAgent;
import data.DataRemote;
import data.UsersAndMessages;
import messaging.MessageManager;
import messaging.MessageManagerRemote;

public class JNDILookup {

	public static final String JNDIPathChat = "ejb:EAR-Chat/JAR_-_Chat/";
	public static final String AgentManagerLookup = JNDIPathChat + AgentManager.class.getSimpleName() + "!"
			+ AgentManagerRemote.class.getName();
	public static final String MessageManagerLookup = JNDIPathChat + MessageManager.class.getSimpleName() + "!"
			+ MessageManagerRemote.class.getName();
	public static final String ChatAgentLookup = JNDIPathChat + ChatAgent.class.getSimpleName() + "!"
			+ Agent.class.getName() + "?stateful";
	public static final String HostAgentLookup = JNDIPathChat + HostAgent.class.getSimpleName() + "!"
			+ Agent.class.getName() + "?stateful";
	
	public static final String DataLookup = JNDIPathChat + UsersAndMessages.class.getSimpleName() + "!"
			+ DataRemote.class.getName();

	private static Context createInitialContext() throws NamingException {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, 
          "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(Context.URL_PKG_PREFIXES, 
          "org.jboss.ejb.client.naming");
        jndiProperties.put(Context.PROVIDER_URL, 
           "http-remoting://localhost:8080");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        return new InitialContext(jndiProperties);
    }
	
	public static <T> T lookUp(String name, Class<T> c) {
		
		T bean = null;
		try {
			Context context = createInitialContext();
			
			System.out.println("Looking up: " + name);
			bean = (T) context.lookup(name);
			
			context.close();

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}
}
