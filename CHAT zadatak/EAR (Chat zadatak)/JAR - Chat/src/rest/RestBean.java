package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
import data.UsersAndMessages;
import lookup.JNDILookup;
import messaging.AgentMessage;
import messaging.MessageManager;
import messaging.MessageManagerRemote;
import models.User;
import models.UserMessage;
import models.UserStatus;

@Path("/")
@Stateless
@LocalBean
public class RestBean implements RestBeanRemote {
	
	//TODO srediti Response
	
	@EJB
	private AgentManagerRemote agm;
	
	@Inject UsersAndMessages data;
	
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
		System.out.println(data);
		postConstruct();
	}
	
	@POST
	@Path("/users/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		System.out.println("LOGIN");
		
		/*
	    for (User u : data.getUsers()) {
			if (u.getUsername().equals(user.getUsername())) {
				if (!u.getPassword().equals(user.getPassword())) {
					return Response.status(400).entity("Wrong password.").build();
				}
				u.setLoggedIn(UserStatus.LOGGED_IN);
				return Response.status(200).entity(u).build();
			}
		}
		*/
		
		AgentMessage msg = new AgentMessage();
		msg.userArgs.put("receiver", "host");
		msg.userArgs.put("method", "login");
		msg.userArgs.put("user", user);
		//msm().post(msg);
		
		return null;
	}
	
	@POST
	@Path("/users/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(User user) {
		System.out.println("REGISTER");
		
		/*
		long maxId = 0;
		for (User u : data.getUsers()) {
			if (u.getId() > maxId) {
				maxId = u.getId();
			}
			if (u.getUsername().equals(user.getUsername())) {
				return Response.status(400).entity("User with given username already exists.").build();
			}
		}
		User newUser = new User(maxId + 1, user.getUsername(), user.getPassword());
		data.getUsers().add(newUser); 
		*/
		
		AgentMessage msg = new AgentMessage();
		msg.userArgs.put("receiver", "host");
		msg.userArgs.put("method", "register");
		msg.userArgs.put("user", user);
		//msm().post(msg);
		
		return Response.status(200).build();
	}

	@GET
	@Path("/users/loggedIn")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getLoggedInUsers() {
		System.out.println("GET_LOGGED_IN");
		List<User> loggedUsers = new ArrayList<>();
		for (User u : data.getUsers()) {
			if (u.getLoggedIn().equals(UserStatus.LOGGED_IN)) {
				loggedUsers.add(u);
			}
		}
		return loggedUsers;
	}

	@DELETE
	@Path("/users/loggedIn/{userId}")
	public Response logOutUser(long userId) {
		System.out.println("LOG_OUT");
		
		/*
		for (User u : data.getUsers()) {
			if (u.getId() == userId) {
				u.setLoggedIn(UserStatus.NOT_LOGGED_IN);
				return Response.status(200).build();
			}
		}
		 */
		
		AgentMessage msg = new AgentMessage();
		msg.userArgs.put("receiver", "host");
		msg.userArgs.put("method", "logout");
		msg.userArgs.put("userId", userId);
		//msm().post(msg);
		return null;
	}

	@GET
	@Path("/users/registered")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getRegisteredUsers() {
		System.out.println("GET_REGISTERED");
		return data.getUsers();
	}

	@POST
	@Path("/messages/all")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<UserMessage> sendMessageToAllUsers(UserMessage msg) {
		System.out.println("SEND_TO_ALL");
		
		/*
		for (int i=0;i<data.getUsers().size();i++) {
			if (data.getUsers().get(i).getLoggedIn().equals(UserStatus.LOGGED_IN)) {
				long receiver = data.getUsers().get(i).getId();
				data.getMessages().add(new UserMessage(msg.getSubject(), msg.getContent(), msg.getSenderId(), receiver));
			}
		}
		 */
		
		AgentMessage amsg = new AgentMessage();
		amsg.userArgs.put("receiver", "chat");
		amsg.userArgs.put("method", "all");
		amsg.userArgs.put("userMessage", msg);
		//msm().post(amsg);
		return null;
	}

	@POST
	@Path("/messages/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserMessage sendMessageToUser(long userId, UserMessage msg) {
		System.out.println("SEND_TO_ONE");
		
		/*
		msg.setId();
		data.getMessages().add(new UserMessage(msg.getSubject(), msg.getContent(), msg.getSenderId(), msg.getReceiverId()));
		 */
		
		AgentMessage amsg = new AgentMessage();
		amsg.userArgs.put("receiver", "chat");
		amsg.userArgs.put("method", "one");
		amsg.userArgs.put("userMessage", msg);
		//msm().post(amsg);
		return null;
	}

	@GET
	@Path("/messages/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserMessage> getAllUserMessages(long userId) {
		System.out.println("ALL_USER_MSGS");
		List<UserMessage> userMessages = new ArrayList<>();
		for(UserMessage m: data.getMessages()) {
			if (m.getReceiverId() == userId || m.getSenderId() == userId) {
				userMessages.add(m);
			}
		}
		return userMessages;
	}

}
