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
		users.add(new User(3, "treci.trecic","treci"));
		users.add(new User(4, "cetvrti.cetvrtic","cetvrti"));
		users.add(new User(5, "peti.petic","peti"));
		
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav sa servera 1", 1, 1));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav sa servera 2", 1, 2));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav sa servera 3", 1, 3));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav sa servera 4", 1, 4));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav sa servera 5", 1, 5));
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
