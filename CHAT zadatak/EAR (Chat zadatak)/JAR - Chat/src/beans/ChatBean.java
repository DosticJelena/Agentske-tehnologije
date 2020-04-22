package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import data.UsersAndMessages;
import models.User;
import models.UserMessage;
import models.UserStatus;

@Stateless
@Path("/")
@LocalBean
public class ChatBean implements ChatRemote {

	@EJB
	private UsersAndMessages data;
	
	public ChatBean() {
		
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "OK";
	}
	
	@POST
	@Path("/users/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(User user) {
		long maxId = 0;
		for (User u : data.getUsers()) {
			if (u.getId() > maxId) {
				maxId = u.getId();
			}
			if (u.getUsername().equals(user.getUsername())) {
				return Response.status(400).entity("User with given username already exists.").build();
			}
		}
		data.getUsers().add(new User(maxId + 1, user.getUsername(), user.getPassword()));
		return Response.status(200).entity(new User(maxId + 1, user.getUsername(), user.getPassword())).build(); 
	}
	
	@POST
	@Path("/users/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		for (User u : data.getUsers()) {
			if (u.getUsername().equals(user.getUsername())) {
				if (!u.getPassword().equals(user.getPassword())) {
					return Response.status(400).entity("Wrong password.").build();
				}
				u.setLoggedIn(UserStatus.LOGGED_IN);
				return Response.status(200).entity(u).build();
			}
		}
		return Response.status(400).entity("There is no registered users with given username and password.").build();
	}
	
	@GET
	@Path("/users/loggedIn")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getLoggedInUsers() {
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
	public Response logOutUser(@PathParam("userId") long userId) {
		for (User u : data.getUsers()) {
			if (u.getId() == userId) {
				u.setLoggedIn(UserStatus.NOT_LOGGED_IN);
				return Response.status(200).build();
			}
		}
		return Response.status(400).build();
	}
	
	@GET
	@Path("/users/registered")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getRegisteredUsers() {
		return data.getUsers();
	}
	
	@POST
	@Path("/messages/all")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<UserMessage> sendMessageToAllUsers(UserMessage msg) {
		for (int i=0;i<data.getUsers().size();i++) {
			if (data.getUsers().get(i).getLoggedIn().equals(UserStatus.LOGGED_IN)) {
				long receiver = data.getUsers().get(i).getId();
				data.getMessages().add(new UserMessage(msg.getSubject(), msg.getContent(), msg.getSenderId(), receiver));
			}
		}
		return data.getMessages();
	}
	
	@POST
	@Path("/messages/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserMessage sendMessageToUser(@PathParam("userId") long userId, UserMessage msg) {
		msg.setId();
		data.getMessages().add(new UserMessage(msg.getSubject(), msg.getContent(), msg.getSenderId(), msg.getReceiverId()));
		System.out.println(msg);
		System.out.println(data.getMessages().size());
		return msg;
	}
	
	@GET
	@Path("/messages/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserMessage> getAllUserMessages(@PathParam("userId") long userId) {
		List<UserMessage> userMessages = new ArrayList<>();
		for(UserMessage m: data.getMessages()) {
			if (m.getReceiverId() == userId || m.getSenderId() == userId) {
				userMessages.add(m);
			}
		}
		return userMessages;
		
	}
}
