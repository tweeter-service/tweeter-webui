package com.example;

import java.io.Serializable;

public class TweeterUser implements Serializable {
	private final String username;
	private final String firstName;
	private final String lastName;
	private final String email;

	public TweeterUser(String username, String firstName, String lastName, String email) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}
}
