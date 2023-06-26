package com.feastfeed.service;

import java.util.List;

import com.feastfeed.dao.IUserDAO;
import com.feastfeed.dto.RecipeDTO;
import com.feastfeed.dto.UserDTO;

public interface IUserService {

	UserDTO fetchById(int id);

	boolean save(UserDTO userDTO) throws Exception;

	List<RecipeDTO> fetchRecipies(String string);

	void setUserDAO(IUserDAO userDAO);

	IUserDAO getUserDAO();
	
	// List<RecipeDTO> fetchLikedRecipies(String string);
 
}