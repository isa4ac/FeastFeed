package com.feastfeed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feastfeed.dao.IRecipeDAO;
import com.feastfeed.dto.RecipeDTO;

@Component
public class RecipeServiceStub implements IRecipeService {

	private IRecipeDAO recipeDAO;
	
	ArrayList<String> stepsList = new ArrayList<>();
	ArrayList<String> ingredientsList = new ArrayList<>();
	
	@Override
	public RecipeDTO fetchById(int id) {
		RecipeDTO recipeDTO = new RecipeDTO();
		recipeDTO.setRecipeId(1);
		recipeDTO.setRecipeTitle("Make a Peanut Butter and Jelly Sandwich");
		
		ingredientsList.add("bread");
		ingredientsList.add("peanut butter");
		ingredientsList.add("jelly");
		
		recipeDTO.setRecipeIngredients(ingredientsList);
		
		stepsList.add("Get two slices of bread");
		stepsList.add("Spread  thin layer of peanut butter on one");
		stepsList.add("Spread a layer of jelly on the other slice of bread");
		stepsList.add("Put the pieces of bread together. Done!");
		
		recipeDTO.setRecipeSteps(stepsList);
		
		return recipeDTO;
	}
	
	@Override
	public IRecipeDAO getRecipeDAO() {
		return recipeDAO;
	}

	@Override
	public void setRecipeDAO(IRecipeDAO recipeDAO) {
		this.recipeDAO = recipeDAO;
	}

	public ArrayList<String> getStepsList() {
		return stepsList;
	}

	public void setStepsList(ArrayList<String> stepsList) {
		this.stepsList = stepsList;
	}

	public ArrayList<String> getIngredientsList() {
		return ingredientsList;
	}

	public void setIngredientsList(ArrayList<String> ingredientsList) {
		this.ingredientsList = ingredientsList;
	}

	@Override
	public boolean save(RecipeDTO recipeDTO) throws Exception {
		boolean result = recipeDAO.save(recipeDTO);
		return result;
	}

	@Override
	public List<RecipeDTO> fetchRecipes(String searchTerm) {
		
		List<RecipeDTO> matchingRecipes = new ArrayList<RecipeDTO>();
		
		if (searchTerm.contains("eanut") || searchTerm.contains("elly")) {
			RecipeDTO recipe = new RecipeDTO();
			recipe.setRecipeTitle("Make a Peanut Butter and Jelly Sandwich");
			recipe.setRecipeSteps(stepsList);
			recipe.setRecipeIngredients(ingredientsList);
			matchingRecipes.add(recipe);
		}
		return matchingRecipes;
	}
}
