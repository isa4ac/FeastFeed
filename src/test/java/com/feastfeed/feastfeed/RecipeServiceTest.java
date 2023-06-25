package com.feastfeed.feastfeed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.feastfeed.dao.IRecipeDAO;
import com.feastfeed.dto.RecipeDTO;
import com.feastfeed.service.IRecipeService;


@SpringBootTest
public class RecipeServiceTest {

	@Autowired
	IRecipeService recipeService;
	List<RecipeDTO> recipes;
	private RecipeDTO recipe;
	
	@MockBean
	private IRecipeDAO recipeDAO;
	
	@Before
	public void setup() throws Exception {
		RecipeDTO recipe = new RecipeDTO();
		recipe.setRecipeTitle("Example Recipe Title");
		recipe.setRecipeId(14);
		Mockito.when(recipeDAO.save(recipe)).thenReturn(true);
		recipeService.setRecipeDAO(recipeDAO);
	}
	
	@Test
	public void saveRecipe_whenRecipeEntered() {
		givenUserIsLoggedIntoFeastFeed();
		whenUserSearchesForRecipe();
		whenUserAddsTextDetails();
		thenRecipeIsSaved();
	}
	
	private void whenUserAddsTextDetails() {
		recipe = new RecipeDTO();
		RecipeDTO recipeDTO = recipes.get(0);
		recipe.setRecipeId(recipeDTO.getRecipeId());
		recipe.setRecipeTitle("Example Recipe Title");
		recipe.setRecipeId(14);
	}

	private void whenUserSearchesForRecipe() {
		recipes = recipeService.fetchRecipes("Peanut Butter");
		
	}

	private void thenRecipeIsSaved() {
		try {
			boolean result = recipeService.save(recipe);
			assertTrue(result);
		}catch (Exception e) {
			fail();
		}
		
		
	}

	@Test
	public void fetchPlants_validateNoResultsForJunkData() {
		givenUserIsLoggedIntoFeastFeed();
		whenTheUserSearchesForJunk();
		thenFeastFeedReturnsZeroResults();
		
	}
	
	@Test
	public void fetchPlants_validateResultsForJelly() {
		givenUserIsLoggedIntoFeastFeed();
		whenTheUserSearchesForJelly();
		thenFeastFeedReturnsJelly();
		
	}

	private void thenFeastFeedReturnsJelly() {
		boolean jellyFound = false;
		for (RecipeDTO recipeDTO : recipes) {
			//in his video he checks all parts of his DTO, not just the title
			//but to do this he uses .getCommon() and for some reason I can't use that method
			//maybe because recipeDTO has ArrayLists instead of just strings
			//so this is only searching by title. subject to change
			if (recipeDTO.getRecipeTitle().contains("Jelly")) {
				jellyFound = true;
			}
		}
		assertTrue(jellyFound);
	}

	private void whenTheUserSearchesForJelly() {
		recipes = recipeService.fetchRecipes("Jelly");		
	}

	private void thenFeastFeedReturnsZeroResults() {
		assertEquals("Number of recipes returned", 0, recipes.size());
		
	}

	private void whenTheUserSearchesForJunk() {
		recipes = recipeService.fetchRecipes("gdhyewuge");
		
		
	}

	private void givenUserIsLoggedIntoFeastFeed() {
		
		
	}
	
	
}
