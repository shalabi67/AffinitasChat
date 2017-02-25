package com.affinitas.chat.messages.data;

import com.affinitas.chat.users.data.User;

public class Message {
	private int messageId;
	private User fromUser;
	private User toUser;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getFromUser() {
		return fromUser;
	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	public User getToUser() {
		return toUser;
	}
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Message)) return false;

		Message message1 = (Message) o;

		if (messageId != message1.messageId) return false;
		if (!fromUser.equals(message1.fromUser)) return false;
		if (!toUser.equals(message1.toUser)) return false;
		return message.equals(message1.message);
	}

	@Override
	public int hashCode() {
		int result = messageId;
		result = 31 * result + fromUser.hashCode();
		result = 31 * result + toUser.hashCode();
		result = 31 * result + message.hashCode();
		return result;
	}
}
