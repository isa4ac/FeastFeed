package com.feastfeed.dto;

import java.util.ArrayList;

class Ingredient {
	private String name; // chopped tomato
	private int unitCount; // 2 
	private String unitName; // tablespoons
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUnitCount() {
		return unitCount;
	}
	public void setUnitCount(int unitCount) {
		this.unitCount = unitCount;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}

public class RecipeDTO {

	private int recipeId;
	private int authorUserId;
	private String recipeTitle;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<String> recipeSteps;
	private String ingredientNames;
	
	public int getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	public int getAuthorUserId() {
		return authorUserId;
	}
	public void setAuthorUserId(int authorUserId) {
		this.authorUserId = authorUserId;
	}
	public String getRecipeTitle() {
		return recipeTitle;
	}
	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public ArrayList<String> getRecipeSteps() {
		return recipeSteps;
	}
	public void setRecipeSteps(ArrayList<String> recipeSteps) {
		this.recipeSteps = recipeSteps;
	}
	
	@Override
	public String toString() {
		return recipeId + " " + recipeTitle + " " + ingredientsToString() + " " + recipeSteps;
	}
	
	public String ingredientsToString() {
		ingredientNames = null;
		for (int i = 0; i < ingredients.size(); i++) {
			Ingredient ing = ingredients.get(i);
			ingredientNames.concat(ing.getName());
		}
		return ingredientNames;
	}
	
	@Override
	public boolean equals(Object obj) {
		//assumes they don't match
		boolean returnValue = false;
		if (obj instanceof RecipeDTO) {
			RecipeDTO incomingRecipe = (RecipeDTO) obj;
			returnValue = incomingRecipe.getRecipeId() == getRecipeId();
		}
		return returnValue;
	}
}
