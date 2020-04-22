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

import data.UsersAndMessages;
import models.User;
import models.UserMessage;

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
	@Produces(MediaType.TEXT_PLAIN)
	public String register() {
		return "Registracija";
	}
	
	@POST
	@Path("/users/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login() {
		return "Logovanje";
	}
	
	@GET
	@Path("/users/loggedIn")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getLoggedInUsers() {
		//TODO: status ulogovan
		return data.getUsers();
	}
	
	@DELETE
	@Path("/users/loggedIn/{userId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String logOutUser(@PathParam("userId") long userId) {
		String poruka = "Odjava korisnika sa id: " + userId;
		return poruka;
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
			long receiver = data.getUsers().get(i).getId();
			data.getMessages().add(new UserMessage(msg.getSubject(), msg.getContent(), msg.getSenderId(), receiver));
		}
		return data.getMessages();
	}
	
	@POST
	@Path("/messages/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserMessage sendMessageToUser(@PathParam("userId") long userId, UserMessage msg) {
		if (msg.getReceiverId() == userId) {
			msg.setId();
			data.getMessages().add(msg);
			System.out.println(msg);
			System.out.println(data.getMessages().size());
		}
		return msg;
	}
	
	@GET
	@Path("/messages/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserMessage> getAllUserMessages(@PathParam("userId") long userId) {
		List<UserMessage> userMessages = new ArrayList<>();
		for(UserMessage m: data.getMessages()) {
			if (m.getReceiverId() == userId) {
				userMessages.add(m);
			}
		}
		return userMessages;
		
	}
}
