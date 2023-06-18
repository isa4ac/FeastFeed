package com.feastfeed.service;

import org.springframework.stereotype.Component;

import com.feastfeed.dto.UserDTO;

@Component
public class UserServiceStub implements IUserService {

	@Override
	public UserDTO fetchById(int id) { // currently, will just make a new user named bob with the passed id
		// will now actually fetch a real user
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(id);
		userDTO.setFirstName("Bob");
		userDTO.setLastName("Bobbington");
		userDTO.setUserName("BobbyB123");
		userDTO.setEmail("BobbyB@test.com");
		userDTO.setBio("Hi I'm Bob");
		// TO-DO: think about logic of adding a friend / following someone
		// TO-DO: think about logic of adding a recipe
		// TO-DO: think about logic of liking a recipe
		
		return userDTO;
	}
	 
	@Override
	public void save(UserDTO specimenDTO) {
		
	}
}
