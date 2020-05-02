package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Startup;
import javax.ejb.Stateful;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import agentmanager.AgentManager;
import agentmanager.AgentManagerRemote;
import lookup.JNDILookup;
import messaging.AgentMessage;
import messaging.MessageManager;
import messaging.MessageManagerRemote;
import models.User;
import models.UserMessage;

@Startup
//@Stateful
@LocalBean
@Path("/")
@Remote(RestBeanRemote.class)
public class RestBean implements RestBeanRemote {
	
	@EJB
	AgentManagerRemote agm;
	
	protected AgentManagerRemote agm() {
		return (AgentManagerRemote)JNDILookup.lookUp(JNDILookup.AgentManagerLookup, AgentManager.class);
	}
	protected MessageManagerRemote msm() {
		return (MessageManagerRemote)JNDILookup.lookUp(JNDILookup.MessageManagerLookup, MessageManager.class);
	}
	public void postConstruct() {
		System.out.println(agm); //null
		agm().startAgent(JNDILookup.ChatAgentLookup);
		agm().startAgent(JNDILookup.HostAgentLookup);
	}
	
	public RestBean() {
		System.out.println("---------------------------------------------------POKRENUO SE");
		postConstruct();
	}
	
	@POST
	@Path("/users/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		System.out.println("LOGIN");
		
		AgentMessage msg = new AgentMessage();
		msg.userArgs.put("receiver", "host");
		msg.userArgs.put("method", "login");
		//msm().post(msg);
		
		return null;
	}
	
	@POST
	@Path("/users/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(User user) {
		System.out.println("REGISTER");
		
		AgentMessage msg = new AgentMessage();
		msg.userArgs.put("receiver", "host");
		msg.userArgs.put("method", "register");
		//msm().post(msg);
		return null;
	}

	@GET
	@Path("/users/loggedIn")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getLoggedInUsers() {
		System.out.println("GET_LOGGED_IN");
		
		return null;
	}

	@DELETE
	@Path("/users/loggedIn/{userId}")
	public Response logOutUser(long userId) {
		System.out.println("LOG_OUT");
		
		AgentMessage msg = new AgentMessage();
		msg.userArgs.put("receiver", "host");
		msg.userArgs.put("method", "logout");
		//msm().post(msg);
		return null;
	}

	@GET
	@Path("/users/registered")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getRegisteredUsers() {
		System.out.println("GET_REGISTERED");
		
		return null;
	}

	@POST
	@Path("/messages/all")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<UserMessage> sendMessageToAllUsers(UserMessage msg) {
		System.out.println("SEND_TO_ALL");
		
		AgentMessage amsg = new AgentMessage();
		amsg.userArgs.put("receiver", "chat");
		amsg.userArgs.put("method", "all");
		//msm().post(amsg);
		return null;
	}

	@POST
	@Path("/messages/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserMessage sendMessageToUser(long userId, UserMessage msg) {
		System.out.println("SEND_TO_ONE");
		
		AgentMessage amsg = new AgentMessage();
		amsg.userArgs.put("receiver", "chat");
		amsg.userArgs.put("method", "one");
		//msm().post(amsg);
		return null;
	}

	@GET
	@Path("/messages/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserMessage> getAllUserMessages(long userId) {
		System.out.println("ALL_USER_MSGS");
		
		return null;
	}

}
