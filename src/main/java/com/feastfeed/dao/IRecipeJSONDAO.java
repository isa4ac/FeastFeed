package com.feastfeed.dao;

import java.util.List;

import com.feastfeed.dto.RecipeJSONDTO;

public interface IRecipeJSONDAO{

	List<RecipeJSONDTO> getAll();

	RecipeJSONDTO getById(int id);

	RecipeJSONDTO create(RecipeJSONDTO recipe);

	RecipeJSONDTO update(RecipeJSONDTO recipe);

	void delete(int id);

	void setAll(List<RecipeJSONDTO> recipes);
	
}