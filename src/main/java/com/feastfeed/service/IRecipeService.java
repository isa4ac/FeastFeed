package com.feastfeed.service;

import java.util.List;

import com.feastfeed.dto.RecipeDTO;

public interface IRecipeService {

	RecipeDTO fetchById(int id);

	boolean save(RecipeDTO recipeDTO) throws Exception;

	List<RecipeDTO> fetchRecipes(String string);

}