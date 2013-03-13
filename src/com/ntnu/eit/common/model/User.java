package com.ntnu.eit.common.model;

public class User {

	private final int ID;
	private final String username;
	private final UserRole role;
	private final String firstName;
	private final String lastName;
	
	public User(int ID, String username, String firstName, String lastName, UserRole role) {
		this.ID = ID;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	public int getID() {
		return ID;
	}

	public String getUsername() {
		return username;
	}

	public UserRole getRole() {
		return role;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}