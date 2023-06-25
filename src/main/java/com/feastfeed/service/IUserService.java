package com.feastfeed.service;

import java.util.List;

import com.feastfeed.dto.RecipeDTO;
import com.feastfeed.dto.UserDTO;

public interface IUserService {

	UserDTO fetchById(int id);

	void save(UserDTO userDTO);

	List<RecipeDTO> fetchRecipies(String string);
	
	// List<RecipeDTO> fetchLikedRecipies(String string);
 
}