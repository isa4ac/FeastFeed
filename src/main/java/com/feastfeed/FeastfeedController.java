package com.feastfeed;

import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
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
	public RecipeDTO 	JSON(Model model) {
		RecipeDTO recipeDTO = recipeService.fetchById(1);
		model.addAttribute("recipeDTO", recipeDTO);
		return recipeDTO;
		
	}
	
	@RequestMapping (value="/readrecipe", method=RequestMethod.GET)
	public String readRecipe(Model model) 
	{
		
		RecipeDTO recipeDTO = recipeService.fetchById(1);
		model.addAttribute("recipeDTO", recipeDTO);
//		model.addAttribute("recipeDTO", new RecipeDTO());
		return "readrecipe";
	}
	
	@RequestMapping (value="/register", method=RequestMethod.GET)
	public String registerAccount(Model model) 
	{
		
//		model.addAttribute("recipeDTO", new RecipeDTO());
		return "register";
	}
	
	@RequestMapping (value="/addrecipe", method=RequestMethod.GET)
	public String addRecipe(Model model, @RequestParam(value="recipeTitle", required=false, defaultValue = "defaultValue") String recipeTitle) 
	{
		RecipeDTO recipeDTO = recipeService.fetchById(1);
		recipeDTO.setRecipeTitle(recipeTitle);
		model.addAttribute("recipeDTO", recipeDTO);
		return "addrecipe";
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
		
	    // Save to text file savedRecipes.txt
	    FileWriter textFileWriter = new FileWriter("savedRecipes.txt", true);
	    //true parameter makes the recipes append to the previous data instead of overriding it
	    
	    textFileWriter.write(recipeDTO.getRecipeTitle() + System.lineSeparator());
	    textFileWriter.write(recipeDTO.getRecipeId() + System.lineSeparator());
	    
	    for (String ingredient : recipeDTO.getRecipeIngredients()) {
	        textFileWriter.write(ingredient + " | ");
	    }
	    
	    textFileWriter.write(System.lineSeparator());
	    
	    for (String step : recipeDTO.getRecipeSteps()) {
	        textFileWriter.write(step + " | ");
	    }
	    
	    textFileWriter.write(System.lineSeparator());
	    textFileWriter.write(System.lineSeparator());
	    
	    textFileWriter.close();

	    // Save to JSON file savedRecipes.json
	    //if no file exists, file is created.
	    File jsonFile = new File("savedRecipes.json");
	    if (!jsonFile.exists()) {
	        jsonFile.createNewFile();
	    }

	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    List<RecipeDTO> recipeList;

	    //if file already exists, json data is read from that file and added
	    //to a list. Then the new recipeDTO is appended to the old data in the same list.
	    //all old data in file is overidden and replaced with new list of recipeDTOs
	    //resulting in updated json file with new recipeDTO
	    try (Reader reader = new FileReader(jsonFile)) {
	        Type recipeListType = new TypeToken<List<RecipeDTO>>() {}.getType();
	        recipeList = gson.fromJson(reader, recipeListType);
	        if (recipeList == null) {
	            recipeList = new ArrayList<>();
	        }
	    }

	    recipeList.add(recipeDTO);

	    try (Writer writer = new FileWriter(jsonFile)) {
	        gson.toJson(recipeList, writer);
	    }
	    
	}
	
	
	
	
}


