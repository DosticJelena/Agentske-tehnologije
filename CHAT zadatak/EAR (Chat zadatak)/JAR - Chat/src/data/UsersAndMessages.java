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
		
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od prvog drugom", 1, 2));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od prvog trecem", 1, 3));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od prvog cetvrtom", 1, 4));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od prvog petom", 1, 5));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od drugog prvom", 2, 1));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od drugog trecem", 2, 3));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od drugog cetvrtom", 2, 4));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od drugog petom", 2, 5));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od treceg prvom", 3, 1));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od treceg drugom", 3, 2));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od treceg cetvrtom", 3, 4));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od treceg petom", 3, 5));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od cetvrtog prvom", 4, 1));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od cetvrtog drugom", 4, 2));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od cetvrtog trecem", 4, 3));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od cetvrtog petom", 4, 5));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od petog prvom", 5, 1));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od petog drugom", 5, 2));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od petog trecem", 5, 3));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od petog cetvrtom", 5, 4));
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
