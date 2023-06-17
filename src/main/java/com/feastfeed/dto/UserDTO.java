package com.feastfeed.dto;

import java.util.ArrayList;

public class UserDTO {

	private int userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;
	private String bio;
//	private ArrayList<UserDTO> friends;
//	private ArrayList<UserDTO> followers;
//	private ArrayList<UserDTO> following;
	// user's recipes ArrayList object here
	// user's LIKED recipes ArrayList object here
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@Override
	public String toString() {
		return userId + " " + firstName + " " + lastName + " " + userName;
	}
	
}
