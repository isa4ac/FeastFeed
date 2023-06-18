package com.feastfeed.service;

import com.feastfeed.dto.RecipeDTO;

public interface IRecipeService {

	RecipeDTO fetchById(int id);

	void save(RecipeDTO recipeDTO);

}