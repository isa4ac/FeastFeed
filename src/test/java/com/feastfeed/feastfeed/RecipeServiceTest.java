package com.feastfeed.feastfeed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
	
	//@BeforeClass 
	//public void setup() {
		//recipeService.set
	//}
	
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
	}

	private void whenUserSearchesForRecipe() {
		recipes = recipeService.fetchRecipes("Peanut Butter");
		
	}

	private void thenRecipeIsSaved() {
		try {
			recipeService.save(recipe);
			assertTrue(true);
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

	private void thenFeastFeedReturnsZeroResults() {
		assertEquals("Number of recipes returned", 0, recipes.size());
		
	}

	private void whenTheUserSearchesForJunk() {
		recipes = recipeService.fetchRecipes("gdhyewuge");
		
		
	}

	private void givenUserIsLoggedIntoFeastFeed() {
		
		
	}
	
	
}
