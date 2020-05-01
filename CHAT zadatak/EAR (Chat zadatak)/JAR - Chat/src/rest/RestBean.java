package rest;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import models.User;

import models.UserMessage;

@LocalBean
@Path("/")
@Remote(RestBeanRemote.class)
public class RestBean implements RestBeanRemote {

	public RestBean() {
		
	}
	
	@POST
	@Path("/users/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		System.out.println("LOGIN");
		return null;
	}
	
	@POST
	@Path("/users/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(User user) {
		System.out.println("REGISTER");
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
		return null;
	}

	@POST
	@Path("/messages/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserMessage sendMessageToUser(long userId, UserMessage msg) {
		System.out.println("SEND_TO_ONE");
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
