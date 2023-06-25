package com.feastfeed.dao;

import com.feastfeed.dto.RecipeDTO;

public interface IRecipeDAO {

	boolean save(RecipeDTO recipeDTO) throws Exception;
}
