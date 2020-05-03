package data;

import java.util.List;

import javax.ejb.Remote;

import models.User;
import models.UserMessage;

@Remote
public interface DataRemote {

	public List<User> getUsers();
	public void setUsers(List<User> users);
	public List<UserMessage> getMessages();
	public void setMessages(List<UserMessage> messages);
	
	public void addUser(User u);
	public void logInUser(User u);
	public void logOutUser(User u);
	
	public void addMessage(UserMessage msg);
	
}
