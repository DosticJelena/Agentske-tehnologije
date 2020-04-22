package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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

import models.User;
import models.UserMessage;

@Stateless
@Path("/")
@LocalBean
public class ChatBean implements ChatRemote {

	private List<User> users = new ArrayList<User>();
	private List<UserMessage> messages = new ArrayList<UserMessage>();
	
	public ChatBean() {
		users.add(new User(1, "prvi.prvic","prvi"));
		users.add(new User(2, "drugi.drugic","drugi"));
		users.add(new User(3, "treci.trecic","treci"));
		users.add(new User(4, "cetvrti.cetvrtic","cetvrti"));
		users.add(new User(5, "peti.petic","peti"));
		
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav sa servera 1", 1, 1));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav sa servera 2", 1, 2));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav sa servera 3", 1, 3));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav sa servera 4", 1, 4));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav sa servera 5", 1, 5));
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
		return users;
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
		return users;
	}
	
	@POST
	@Path("/messages/all")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<UserMessage> sendMessageToAllUsers(UserMessage msg) {
		for (User u : users) {
			msg.setReceiverId(u.getId());
			messages.add(msg);
		}
		return messages;
	}
	
	@POST
	@Path("/messages/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserMessage sendMessageToUser(@PathParam("userId") long userId, UserMessage msg) {
		if (msg.getReceiverId() == userId) {
			messages.add(msg);
			System.out.println(msg);
			System.out.println(messages.size());
		}
		return msg;
	}
	
	@GET
	@Path("/messages/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserMessage> getAllUserMessages(@PathParam("userId") long userId) {
		List<UserMessage> userMessages = new ArrayList<>();
		for(UserMessage m: messages) {
			if (m.getReceiverId() == userId) {
				userMessages.add(m);
			}
		}
		return userMessages;
		
	}
}
