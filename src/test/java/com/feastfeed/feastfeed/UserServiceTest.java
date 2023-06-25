package com.feastfeed.feastfeed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.feastfeed.dto.RecipeDTO;
import com.feastfeed.dto.UserDTO;
import com.feastfeed.service.IUserService;

@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	IUserService userService;
	List<RecipeDTO> recipies;
	List<UserDTO> users;
	
	@Test
	public void saveUser_whenUserEntered() {
		givenUserIsLoggedInToFeastFeed();
		whenUserSearchesForPBJ();
		whenUserAddsTextDetails();
		thenRecipeIsSaved();
	}
	
	private void whenUserSearchesForPBJ() {
		recipies = userService.fetchRecipies("Peanut Butter & Jelly");
		
	}

	private void whenUserAddsTextDetails() {
		RecipeDTO recipe = new RecipeDTO();
		UserDTO userDTO = users.get(0);
		recipe.setAuthorUserId(userDTO.getUserId());
		recipe.setRecipeTitle("");
		
	}

	private void thenRecipeIsSaved() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void fetchRecipies_validateNoResultsForJunkData() {
		givenUserIsLoggedInToFeastFeed();
		whenTheUserSearchesForJunk();
		thenRecipeSearchReturnsZeroResults();
		
	}

	private void givenUserIsLoggedInToFeastFeed() {

		
		
	}

	private void whenTheUserSearchesForJunk() {
		
		recipies = userService.fetchRecipies("kajsd;luaopuidfjo;aj;sd");
		
	}

	private void thenRecipeSearchReturnsZeroResults() {
		
		assertEquals(0, recipies.size());
		
	}
	
}
