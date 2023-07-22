package com.feastfeed.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feastfeed.dao.IRecipeJSONDAO;
import com.feastfeed.dto.RecipeJSONDTO;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class RecipeJSONService implements IRecipeJSONDAO{
	private final File jsonFile;
	private final ObjectMapper objectMapper;
	
	public RecipeJSONService() throws IOException{
		this.objectMapper = new ObjectMapper();
        this.jsonFile = new ClassPathResource("/data/recipeJSON.json").getFile();
	}
	
	// Reads all recipes from the data/recipeJSON.json file
	private List<RecipeJSONDTO> readRecipesFromFile(){
		try {
			return objectMapper.readValue(jsonFile, new TypeReference<List<RecipeJSONDTO>>(){});
		}
		catch (IOException e) {
			System.out.print("ERROR:\t FILE NOT FOUND\n");
			System.out.println(e);
			return new ArrayList<>();
		}
	}
	
	// Writes all recipes to the data/recipeJSON.json file
	private void writeRecipesToFile(List<RecipeJSONDTO> recipes) {
		 try {
			 objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, recipes);
	        } catch (IOException e) {
	        	System.out.print("ERROR:\t CANNOT WRITE! \t FILE NOT FOUND");
	        }
	}
	
	// Get all recipes in the file
	@Override
	public List<RecipeJSONDTO> getAll(){
		List<RecipeJSONDTO> recipes = readRecipesFromFile();
		int newId = 0;
	    for (RecipeJSONDTO recipe : recipes) {
	        recipe.setId(newId++);
	    }
	    return recipes;
	}
	
	@Override public void setAll(List<RecipeJSONDTO> recipes){
		int newId = 0;
	    for (RecipeJSONDTO recipe : recipes) {
	        recipe.setId(newId++);
	    }
	    writeRecipesToFile(recipes);
	}
	
	// Get a recipe by its id
	@Override
	public RecipeJSONDTO getById(int id) {
		
		return getAll().get(id);
	}
	
	// Adding crud operations
	
	@Override
	public RecipeJSONDTO create(RecipeJSONDTO recipe) {
		List<RecipeJSONDTO> recipes = getAll();
		
		int nextId = recipes.stream().mapToInt(RecipeJSONDTO::getId).max().orElse(-1);
		RecipeJSONDTO newRecipe = RecipeJSONDTO.builder()
												.id(nextId)
												.name(recipe.getName())
												.url(recipe.getUrl())
												.description(recipe.getDescription())
												.author(recipe.getAuthor())
												.ingredients(recipe.getIngredients())
												.method(recipe.getMethod())
												.build();
		recipes.add(newRecipe);
		setAll(recipes);
		recipes = getAll();
		return recipes.get(recipes.size()-1);
	}
	
	@Override
	public RecipeJSONDTO update(RecipeJSONDTO recipe) {
		List<RecipeJSONDTO> recipes = readRecipesFromFile();
		int index =-1;
		for (int i=0; i<recipes.size(); i++) {
			if(recipes.get(i).getId() == recipe.getId()){
				index = i;
				break;
			}
		}
		if(index != -1) {
			recipes.set(index, recipe);
			setAll(recipes);
			return recipe;
		}
		return null;
	}
	
	@Override
	public void delete(int id) {
		List<RecipeJSONDTO> recipes = getAll();
		 for (Iterator<RecipeJSONDTO> iterator = recipes.iterator(); iterator.hasNext(); ) {
		        RecipeJSONDTO recipe = iterator.next();
		        if (recipe.getId() == id) {
		            iterator.remove();
		            break;
		        }
		    }
		setAll(recipes);
	}
	
	// Adding Pagination methods
	public List<RecipeJSONDTO> getPaginatedRecipes(int page, int pageSize) {
        List<RecipeJSONDTO> allRecipes = getAll();
        int startIndex = (page>1)?(page - 1) * pageSize+1 : page;
        int endIndex = Math.min(startIndex + pageSize, allRecipes.size());
        List<RecipeJSONDTO> paginatedRecipes = new ArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            RecipeJSONDTO originalRecipe = allRecipes.get(i);
            RecipeJSONDTO paginatedRecipe = new RecipeJSONDTO();
            paginatedRecipe.setId(i);
            paginatedRecipe.setName(originalRecipe.getName());
            paginatedRecipe.setUrl(originalRecipe.getUrl());
            paginatedRecipe.setMethod(originalRecipe.getMethod());
            paginatedRecipe.setAuthor(originalRecipe.getAuthor());
            paginatedRecipe.setIngredients(originalRecipe.getIngredients());
            paginatedRecipe.setDescription(originalRecipe.getDescription());
            paginatedRecipes.add(paginatedRecipe);
        }
        return paginatedRecipes;
    }
	
	public int getTotalPages(int pageSize) {
        List<RecipeJSONDTO> allRecipes = getAll();
        return (int) Math.ceil((double) allRecipes.size() / pageSize);
    }

	// Fuzzy Search
	// Vague search for the given keyword in all fields of RecipeJSONDTO
    public List<RecipeJSONDTO> fuzzySearch(String keyword) {
    	List<RecipeJSONDTO> recipes = getAll();
        return recipes.stream()
                .filter(recipe -> recipeContainsKeyword(recipe, keyword))
                .collect(Collectors.toList());
    }

    // Helper method to check if the given recipe contains the keyword in any field
    private boolean recipeContainsKeyword(RecipeJSONDTO recipe, String keyword) {
        String lowercaseKeyword = keyword.toLowerCase();
        return recipe.toString().toLowerCase().contains(lowercaseKeyword);
    }


}