package data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import models.User;
import models.UserMessage;
import models.UserStatus;

@Singleton
@LocalBean
public class UsersAndMessages implements DataRemote {

	private List<User> users = new ArrayList<User>();
	private List<UserMessage> messages = new ArrayList<UserMessage>();
	
	public UsersAndMessages() {
		users.add(new User(1, "prvi.prvic","prvi"));
		users.add(new User(2, "drugi.drugic","drugi"));
		users.add(new User(3, "treci.trecic","treci"));
		users.add(new User(4, "cetvrti.cetvrtic","cetvrti"));
		
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od prvog drugom", 1, 2));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od prvog trecem", 1, 3));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od prvog cetvrtom", 1, 4));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od drugog prvom", 2, 1));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od drugog trecem", 2, 3));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od drugog cetvrtom", 2, 4));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od treceg prvom", 3, 1));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od treceg drugom", 3, 2));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od treceg cetvrtom", 3, 4));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od cetvrtog prvom", 4, 1));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od cetvrtog drugom", 4, 2));
		messages.add(new UserMessage("Subject (Predefined)", "Pozdrav od cetvrtog treceg", 4, 3));
	}

	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public List<UserMessage> getMessages() {
		return messages;
	}

	@Override
	public void setMessages(List<UserMessage> messages) {
		this.messages = messages;
	}

	@Override
	public void addUser(User u) {
		users.add(u);
	}

	@Override
	public void changeUserLoggedInStatus(User user) {
		for (User u : users) {
			if (u.getUsername().equals(user.getUsername())) {
				if (u.getLoggedIn().equals(UserStatus.NOT_LOGGED_IN)) {
					u.setLoggedIn(UserStatus.LOGGED_IN);
				} else {
					u.setLoggedIn(UserStatus.NOT_LOGGED_IN);
				}
			}
		}
	}

	@Override
	public void addMessage(UserMessage msg) {
		messages.add(msg);
	}
}
