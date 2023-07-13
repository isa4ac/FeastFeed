package com.feastfeed;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	@RequestMapping("/saveuser") // needs tests to function?
	public String saveUser(UserDTO userDTO) {
		userDTO.setUserId(1010); // TO-DO replace with auto generated ID
		return "index";
	}
	
	@RequestMapping(value="/adduser", method=RequestMethod.GET)
	public String addUser(Model model, @RequestParam(value="firstname", required=false, defaultValue ="") String firstName) {
		UserDTO userDTO = userServiceStub.fetchById(1010);
		userDTO.setFirstName(firstName);
		model.addAttribute("userDTO", userDTO);
		return "index";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String read(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		return "index";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET, headers={"content-type=text/json"})
	@ResponseBody
	public UserDTO readUserJSON(Model model) {
		UserDTO userDTO = userServiceStub.fetchById(1010);
		model.addAttribute("userDTO", userDTO);
		return userDTO;
	}
	
	@RequestMapping (value="/start", method=RequestMethod.GET, headers= {"content-type=text/json"})
	@ResponseBody
	public RecipeDTO readRecipeJSON(Model model) {
		RecipeDTO recipeDTO = recipeService.fetchById(1);
		model.addAttribute("recipeDTO", recipeDTO);
		return recipeDTO;
		
	}
	
	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String readRecipe(Model model) {
		model.addAttribute("recipeDTO", new RecipeDTO());
		return "start";
	}
	
	@RequestMapping (value="/addrecipe", method=RequestMethod.GET)
	public String addRecipe(Model model, @RequestParam(value="recipeTitle", required=false, defaultValue = "defaultValue") String recipeTitle) 
	{
		RecipeDTO recipeDTO = recipeService.fetchById(1);
		recipeDTO.setRecipeTitle(recipeTitle);
		model.addAttribute("recipeDTO", recipeDTO);
		return "start";
	}
	
	@RequestMapping (value="/saverecipe")
	public String saveRecipe(RecipeDTO recipeDTO) throws IOException {
		//recipeDTO.setRecipeId(12);
		saveRecipeToFile(recipeDTO);
		return "start";
	}
	
	@RequestMapping("/searchRecipes")
	public String searchRecipes(@RequestParam(value="searchTerm", required=false, defaultValue = "") String searchTerm) 
	{
		String enhancedTerm = searchTerm + "";
		List<RecipeDTO> fetchRecipes = recipeService.fetchRecipes(searchTerm);
		return "start";
	}
	
	@RequestMapping("/searchRecipesAll")
	public String searchRecipesAll(@RequestParam Map<String,String> requestParams) 
	{
		int params = requestParams.size();
		requestParams.get("searchTerm");
		return "start";
	}
	
	public void saveRecipeToFile(RecipeDTO recipeDTO) throws IOException {
		
		//put all elements of current recipeDTO into an ArrayList of 
		//type string to be saved in text file
		
		ArrayList<String> recipeDataLines = new ArrayList<>();
		String id = "" + recipeDTO.getRecipeId();
		String ingredients = "";
		String steps = "";
		
		for(int i = 0; i < recipeDTO.getRecipeIngredients().size(); i++)
		{
		    ingredients += "" + recipeDTO.getRecipeIngredients().get(i) + " | ";
		}
		
		for(int i = 0; i < recipeDTO.getRecipeSteps().size(); i++)
		{
		    steps += "" + recipeDTO.getRecipeSteps().get(i) + " | ";
		}
		
		recipeDataLines.add(recipeDTO.getRecipeTitle());
		recipeDataLines.add(id);
		recipeDataLines.add(ingredients);
		recipeDataLines.add(steps);
		recipeDataLines.add(" ");
		
		//write them into savedRecipes
		
		FileWriter writer = new FileWriter("savedRecipes.txt", true); 
		//the true parameter sets the filewriter to append mode, 
		//which adds to the file without erasing the data previously in it
		
		for(String str: recipeDataLines) {
		  try {
			writer.write(str + System.lineSeparator());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		writer.close();
	}
	
	
	
	
}


