package com.feastfeed.dto;

import java.util.ArrayList;

public class RecipeDTO {

	private int recipeId;
	private int authorUserId;
	private String recipeTitle;
	private ArrayList<String> recipeIngredients;
	private ArrayList<String> recipeSteps;
	
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
	public ArrayList<String> getRecipeIngredients() {
		return recipeIngredients;
	}
	public void setRecipeIngredients(ArrayList<String> recipeIngredients) {
		this.recipeIngredients = recipeIngredients;
	}
	public ArrayList<String> getRecipeSteps() {
		return recipeSteps;
	}
	public void setRecipeSteps(ArrayList<String> recipeSteps) {
		this.recipeSteps = recipeSteps;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return recipeId + " " + recipeTitle + " " + recipeIngredients + " " + recipeSteps;
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
