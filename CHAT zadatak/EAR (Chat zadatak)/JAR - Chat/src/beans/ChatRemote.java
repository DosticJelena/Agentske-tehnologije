package beans;

import java.util.List;

import javax.ejb.Remote;

import models.User;
import models.UserMessage;

@Remote
public interface ChatRemote {

	public String test();
	
	public String login();

	public String register();

	public List<User> getLoggedInUsers();
	
	public String logOutUser(long userId);
	
	public List<User> getRegisteredUsers();
	
	public List<UserMessage> sendMessageToAllUsers(UserMessage msg);
	
	public UserMessage sendMessageToUser(long userId, UserMessage msg);
	
	public List<UserMessage> getAllUserMessages(long userId);
}
