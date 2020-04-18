package beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/")
@LocalBean
public class ChatBean {

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
	@Produces(MediaType.TEXT_PLAIN)
	public String getLoggedInUsers() {
		return "Logovani korisnici";
	}
	
	@DELETE
	@Path("/users/loggedIn/{userId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String logOutUser(@PathParam("userId") int userId) {
		String poruka = "Odjava korisnika sa id: " + userId;
		return poruka;
	}
	
	@GET
	@Path("/users/registered")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRegisteredUsers() {
		return "Registrovani korisnici";
	}
	
	@POST
	@Path("/messages/all")
	@Produces(MediaType.TEXT_PLAIN)
	public String sendMessageToAllUsers() {
		return "Slanje poruke svim korisnicima";
	}
	
	@POST
	@Path("/messages/{userId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sendMessageToUser(@PathParam("userId") int userId) {
		String poruka = "Slanje poruke kornisku sa id: " + userId;
		return poruka;
	}
	
	@GET
	@Path("/messages/{userId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllUserMessages(@PathParam("userId") int userId) {
		String poruka = "Sve poruke korniska sa id: " + userId;
		return poruka;
	}
}
