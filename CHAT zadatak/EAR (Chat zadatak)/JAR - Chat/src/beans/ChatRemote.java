package beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ws.rs.core.Response;

import models.User;
import models.UserMessage;

@Remote
public interface ChatRemote {

	public String test();
	
	public User login(User user);

	public User register(User user);

	public List<User> getLoggedInUsers();
	
	public boolean logOutUser(long userId);
	
	public List<User> getRegisteredUsers();
	
	public List<UserMessage> sendMessageToAllUsers(UserMessage msg);
	
	public UserMessage sendMessageToUser(long userId, UserMessage msg);
	
	public List<UserMessage> getAllUserMessages(long userId);
}
