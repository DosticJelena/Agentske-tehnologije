package data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import models.User;
import models.UserMessage;

@Singleton
@LocalBean
public class UsersAndMessages {

	private List<User> users = new ArrayList<User>();
	private List<UserMessage> messages = new ArrayList<UserMessage>();
	
	public UsersAndMessages() {
		users.add(new User(1, "prvi.prvic","prvi"));
		users.add(new User(2, "drugi.drugic","drugi"));
		
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od prvog drugom", 1, 2));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od drugog prvom", 2, 1));
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<UserMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<UserMessage> messages) {
		this.messages = messages;
	}
	
}
