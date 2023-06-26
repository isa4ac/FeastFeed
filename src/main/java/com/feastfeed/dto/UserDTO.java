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
	private ArrayList<Integer> recipeIDs;
	private ArrayList<Integer> likedRecipeIDs;
//	private ArrayList<UserDTO> followers;
//	private ArrayList<UserDTO> following;
	
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

	public ArrayList<Integer> getRecipeIDs() {
		return recipeIDs;
	}

	public void setRecipeIDs(ArrayList<Integer> recipeIDs) {
		this.recipeIDs = recipeIDs;
	}
	
	public void addRecipeID(Integer recipeID) {
		if (this.recipeIDs != null) {
			this.recipeIDs.add(recipeID);
		} else {
			this.recipeIDs = new ArrayList<Integer>();
			this.recipeIDs.add(recipeID);
		}
	}

	public ArrayList<Integer> getLikedRecipeIDs() {
		return likedRecipeIDs;
	}

	public void setLikedRecipeIDs(ArrayList<Integer> likedRecipeIDs) {
		this.likedRecipeIDs = likedRecipeIDs;
	}
	
	public void addLikedRecipeID(Integer recipeID) {
		if (this.likedRecipeIDs != null) {
			this.likedRecipeIDs.add(recipeID);
		} else {
			this.likedRecipeIDs = new ArrayList<Integer>();
			this.likedRecipeIDs.add(recipeID);
		}
	}

	@Override
	public String toString() {
		return userId + " " + firstName + " " + lastName + " " + userName;
	}
	
	@Override
	public boolean equals(Object obj) {
		// assume they don't match
		boolean returnValue = false;
		if (obj instanceof UserDTO) {
			UserDTO incomingUser = (UserDTO) obj;
			returnValue = incomingUser.getUserId() == getUserId();
		}
		return returnValue;
	}
	
}
