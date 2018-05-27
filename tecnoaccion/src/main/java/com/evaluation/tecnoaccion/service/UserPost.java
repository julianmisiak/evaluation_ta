package com.evaluation.tecnoaccion.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPost {
	private String email;
	private String firstName;
	private String lastName;

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UserPost [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
