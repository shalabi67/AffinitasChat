package com.affinitas.chat.users.data;

/**
 * User class that holds chat user information.
 * @author mohammad
 */
public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String nickName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	//TODO: keep an eye o this, it may be need to be replaced by a builder pattern
	public static User create(String firstName, String lastName, String nickName) {
		User user = new User();
		user.setNickName(nickName);
		user.setFirstName(firstName);
		user.setLastName(lastName);

		return user;
	}
}
