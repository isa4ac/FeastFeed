package com.feastfeed;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feastfeed.dto.RecipeDTO;
import com.feastfeed.dto.UserDTO;
import com.feastfeed.service.IRecipeService;
import com.feastfeed.service.IUserService;

@Controller
public class FeastfeedController {
	
	@Autowired
	private IRecipeService recipeService;
	
	@Autowired
	private IUserService userServiceStub;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/start")
	public String start() {
		return "start";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String read(Model model) {
		UserDTO userDTO = userServiceStub.fetchById(01);
		model.addAttribute("userDTO", userDTO);
		return "index";
	}
	
	@RequestMapping (value="/start", method=RequestMethod.GET, headers= {"content-type=text/json"})
	@ResponseBody
	public RecipeDTO readJSON(Model model) {
		RecipeDTO recipeDTO = recipeService.fetchById(1);
		model.addAttribute("recipeDTO", recipeDTO);
		return recipeDTO;
		
	}
	
	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String readRecipe(Model model) {
		model.addAttribute("recipeDTO", new RecipeDTO());
		return "start";
	}
	
	@RequestMapping (value="/addRecipe", method=RequestMethod.GET)
	public String addRecipe(Model model, @RequestParam(value="recipeTitle", required=false, defaultValue = "") String recipeTitle) 
	{
		RecipeDTO recipeDTO = recipeService.fetchById(1);
		recipeDTO.setRecipeTitle(recipeTitle);
		model.addAttribute("recipeDTO", recipeDTO);
		return "start";
	}
	
	@RequestMapping (value="/saverecipe")
	public String saveRecipe(RecipeDTO recipeDTO) {
		recipeDTO.setRecipeId(12);
		return "start";
	}
	
	@RequestMapping("/searchRecipes")
	public String searchRecipes(@RequestParam(value="searchTerm", required=false, defaultValue = "") String searchTerm) 
	{
		String enhancedTerm = searchTerm + "";
		recipeService.fetchRecipes(searchTerm);
		return "start";
	}
	
	@RequestMapping("/searchRecipesAll")
	public String searchRecipesAll(@RequestParam Map<String,String> requestParams) 
	{
		int params = requestParams.size();
		requestParams.get("searchTerm");
		return "start";
	}
	
}


