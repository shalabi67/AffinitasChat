package com.affinitas.chat.messages.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * provide storage and management for Messages
 */
@Component
public class MessagesRepository {
	//sent messages
	private Map<Integer, Message> messages = new HashMap<>();
	//messages send to a user:MAP<USERID, MESSAGES>
	private Map<Integer, List<Message>> toUserMessages = new HashMap<>();

	//last message id
	private int lastMessageId = 0;

	public Message getUserLastMessage(int userId) {
		List<Message> messages = toUserMessages.get(userId);
		if(messages == null || messages.size() == 0)
			return null;
		return messages.get(messages.size()-1);
	}

	public Message addMessage(Message message) {
		lastMessageId++;
		message.setMessageId(lastMessageId);
		messages.put(message.getMessageId(), message);

		linkMessageToUser(message);

		return message;
	}
	private void linkMessageToUser(Message message) {
		int toUserId = message.getToUser().getId();
		List<Message> list = toUserMessages.get(toUserId);
		if(list == null) {
			list = new ArrayList<>();
		}

		list.add(message);
		toUserMessages.put(toUserId, list);
	}
}
