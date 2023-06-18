package com.feastfeed.dto;

import java.util.ArrayList;

public class RecipeDTO {

	private int recipeId;
	private String recipeTitle;
	private ArrayList<String> recipeIngredients;
	private ArrayList<String> recipeSteps;
	
	public int getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
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
	
}
