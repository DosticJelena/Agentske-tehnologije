package models;

import java.io.Serializable;

public class UserMessage implements Serializable {

	private String subject;
	private String content;
	private long senderId;
	private long receiverId;
	
	public UserMessage() {
		
	}
	
	public UserMessage(String subject, String content, long sender, long receiver) {
		super();
		this.subject = subject;
		this.content = content;
		this.senderId = sender;
		this.receiverId = receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long sender) {
		this.senderId = sender;
	}

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiver) {
		this.receiverId = receiver;
	}

	@Override
	public String toString() {
		return "UserMessage [subject=" + subject + ", content=" + content + ", senderId=" + senderId + ", receiverId="
				+ receiverId + "]";
	}
	
	
}
