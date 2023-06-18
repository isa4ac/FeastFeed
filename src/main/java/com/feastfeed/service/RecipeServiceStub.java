package com.feastfeed.service;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.feastfeed.dto.RecipeDTO;

@Component
public class RecipeServiceStub implements IRecipeService {

	@Override
	public RecipeDTO fetchById(int id) {
		RecipeDTO recipeDTO = new RecipeDTO();
		recipeDTO.setRecipeId(1);
		recipeDTO.setRecipeTitle("Make a Peanut Butter and Jelly Sandwich");
		
		ArrayList<String> ingredientsList = new ArrayList<>();
		ingredientsList.add("bread");
		ingredientsList.add("peanut butter");
		ingredientsList.add("jelly");
		
		recipeDTO.setRecipeIngredients(ingredientsList);
		
		ArrayList<String> stepsList = new ArrayList<>();
		stepsList.add("Get two slices of bread");
		stepsList.add("Spread  thin layer of peanut butter on one");
		stepsList.add("Spread a layer of jelly on the other slice of bread");
		stepsList.add("Put the pieces of bread together. Done!");
		
		recipeDTO.setRecipeSteps(stepsList);
		
		return recipeDTO;
	}
	
	@Override
	public void save(RecipeDTO recipeDTO) {
		
	}
}
