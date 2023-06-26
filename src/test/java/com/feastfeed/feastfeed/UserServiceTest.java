package com.feastfeed.feastfeed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.feastfeed.dao.IUserDAO;
import com.feastfeed.dto.RecipeDTO;
import com.feastfeed.dto.UserDTO;
import com.feastfeed.service.IRecipeService;
import com.feastfeed.service.IUserService;

@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IRecipeService recipeService;
	List<RecipeDTO> recipies;
	List<UserDTO> users;
	private UserDTO user;
	
	@MockBean
	private IUserDAO userDAO;
	
	@BeforeEach
	public void setup() throws Exception {
		UserDTO user = new UserDTO();
		user.setFirstName("Isaac");
		user.setUserId(83);
		Mockito.when(userDAO.save(user)).thenReturn(true);
		userService.setUserDAO(userDAO);
	}
	
	@Test
	public void saveUser_whenUserEntered() { // story
		givenUserIsLoggedInToFeastFeed();
		whenUserSearchesForPBJ(); // searches for recipe
		whenUserAddsTextDetails(); // adds new recipe
		thenUserIsSaved(); //  
	}
	
	private void whenUserSearchesForPBJ() {
		recipies = userService.fetchRecipies("Peanut Butter & Jelly");
		
	}

	private void whenUserAddsTextDetails() {
		user = new UserDTO();
		RecipeDTO recipeDTO = recipies.get(0);
		user.addRecipeID(recipeDTO.getRecipeId());
		user.setFirstName("Isaac");
		user.setUserId(83);
	}

	private void thenUserIsSaved() {
		try {
			userService.save(user);
			assertTrue(true);
		} catch (Exception e) {
			// we should not get here if test passes
			fail();
		}
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
