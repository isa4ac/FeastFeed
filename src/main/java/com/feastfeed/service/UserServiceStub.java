package com.feastfeed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feastfeed.dao.IUserDAO;
import com.feastfeed.dto.RecipeDTO;
import com.feastfeed.dto.UserDTO;

@Component
public class UserServiceStub implements IUserService {
	
	private IUserDAO userDAO;

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
	public boolean save(UserDTO userDTO) throws Exception {
		boolean result = userDAO.save(userDTO);
		return result;
	}

	@Override
	public List<RecipeDTO> fetchRecipies(String searchTerm) {
		// stub out recipe fetch mechanism
		List<RecipeDTO> matchingRecipies = new ArrayList<RecipeDTO>();
		
		if (searchTerm.contains("Peanut Butter") || searchTerm.contains("Jelly")) {
			RecipeDTO recipe = new RecipeDTO();
			ArrayList<String> stepsList = new ArrayList<>();
			stepsList.add("PB&J Step 1");
			stepsList.add("PB&J Step 2");
			ArrayList<String> ingredientsList = new ArrayList<>();
			ingredientsList.add("Peanut Butter");
			ingredientsList.add("Jelly");
			ingredientsList.add("Bread");
			recipe.setRecipeId(999);
			recipe.setRecipeTitle("Peanut Butter & Jelly");
			recipe.setRecipeIngredients(ingredientsList);
			recipe.setRecipeSteps(stepsList);
			
			matchingRecipies.add(recipe);
		}
		
		return matchingRecipies;
	}

	@Override
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	@Override
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
