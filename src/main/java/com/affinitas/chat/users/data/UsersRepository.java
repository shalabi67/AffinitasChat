package com.affinitas.chat.users.data;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * provide storage and management for Users
 */
@Component
public class UsersRepository {
	private Map<Integer, User> users = new HashMap<>();
	private int lastUserId = 0;

	public User getUserById(int userId) {
		return users.get(userId);
	}

	public User addUser(User user) {
		lastUserId++;
		user.setId(lastUserId);

		users.put(user.getId(), user);
		return user;
	}

	@PostConstruct
	private void initRepository() {
		User user1 = User.create("Mohammad", "Shalabi", "sha");
		addUser(user1);

		User user2 = User.create("Salem", "Saeed", "sa");
		addUser(user2);
	}
}
