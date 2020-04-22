package models;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String username;
	private String password;
	
	public User(long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User:" + username;
	}

}

