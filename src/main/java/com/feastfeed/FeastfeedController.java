package com.feastfeed;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.feastfeed.dto.RecipeDTO;
import com.feastfeed.dto.RecipeJSONDTO;
import com.feastfeed.dto.UserDTO;
import com.feastfeed.service.IRecipeService;
import com.feastfeed.service.IUserService;
import com.feastfeed.service.RecipeJSONService;

@Controller
public class FeastfeedController {
	
	@Autowired
	private IRecipeService recipeService;
	
	@Autowired
	private IUserService userServiceStub;
	
	@Autowired 
	private RecipeJSONService recipeJSONService;
	
	// JSON CRUD
	@GetMapping("/rest/all")
	@ResponseBody
	public ResponseEntity<List<RecipeJSONDTO>> getAllRecipes(){
		List<RecipeJSONDTO> recipes = recipeJSONService.getAll();
		return ResponseEntity.ok(recipes);
	}
	
	@GetMapping("/rest/{id}")
	@ResponseBody
	public ResponseEntity<RecipeJSONDTO> getRecipeById(@PathVariable int id){
		RecipeJSONDTO recipe = recipeJSONService.getById(id);
		if (recipe != null) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@PostMapping("/rest/create/new")
	@ResponseBody
    public ResponseEntity<RecipeJSONDTO> createRecipe(@RequestBody RecipeJSONDTO recipe) {
		RecipeJSONDTO createdRecipe = recipeJSONService.create(recipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecipe);
    }
	
	@PostMapping("/rest/delete/{id}")
	@ResponseBody
    public ResponseEntity<String> deleteRecipeJSON(@PathVariable int id) {
		recipeJSONService.delete(id);
        return ResponseEntity.ok("Success");
    }
	
	@PostMapping("/rest/update/{id}")
    public ResponseEntity<RecipeJSONDTO> updateRecipe(@PathVariable int id, @RequestBody RecipeJSONDTO recipe) {
        // Ensure that the id in the path matches the id in the recipe object
        if (!(id == recipe.getId())) {
            return ResponseEntity.badRequest().build();
        }
        RecipeJSONDTO updatedRecipe = recipeJSONService.update(recipe);
        if (updatedRecipe != null) {
            return ResponseEntity.ok(updatedRecipe);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
   
	// ---
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String pagination(Model model,@RequestParam(defaultValue = "1") int page) {
		int pageSize = 9; //Number of recipes per page
		List<RecipeJSONDTO> recipes = recipeJSONService.getPaginatedRecipes(page, pageSize);
		int totalPages = recipeJSONService.getTotalPages(pageSize);
		model.addAttribute("recipes", recipes);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("page",page);
		return "home";
	}
	
	@GetMapping("/recipe/{id}")
	public String getById(Model model,@PathVariable String id){
		RecipeJSONDTO recipe = recipeJSONService.getById(Integer.parseInt(id));
		if (recipe != null) {
			model.addAttribute("recipe",recipe);
            return "readrecipe";
        } else {
            return "error";
        }
	}
	
	@PostMapping("/recipe/delete/{id}")
    public String deleteRecipe(@ModelAttribute RecipeJSONDTO recipe, RedirectAttributes redirectAttributes) {
		final String temp = recipe.getName();
		recipeJSONService.delete(recipe.getId());
		redirectAttributes.addFlashAttribute("success", "Recipe deleted successfully.");
		return "redirect:/index";
    }
	
	@GetMapping("/search")
    public String fuzzySearch(Model model, @RequestParam("term") String searchTerm) {
		List<RecipeJSONDTO> searchResults = recipeJSONService.fuzzySearch(searchTerm);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("term",searchTerm);
        return "search";
    }
	
	 @GetMapping("/update/{id}")
	    public String showUpdateForm(@PathVariable("id") int recipeId, Model model) {
	        // Fetch the recipe from the database or any data source
	        RecipeJSONDTO recipe = recipeJSONService.getById(recipeId);

	        model.addAttribute("recipe", recipe);
	        return "update";
	    }

	    @PostMapping("/update")
	    public String updateRecipe(@ModelAttribute RecipeJSONDTO updatedRecipe, RedirectAttributes redirectAttributes) {
	        recipeJSONService.update(updatedRecipe);
	        redirectAttributes.addFlashAttribute("success", "Recipe updated successfully.");
	        return "redirect:/index"; 
	    }
	    
	    @GetMapping("/create")
	    public String showCreateForm(Model model) {
	        RecipeJSONDTO newRecipe = new RecipeJSONDTO();

	        model.addAttribute("newRecipe", newRecipe);
	        return "create";
	    }
	    
	    @PostMapping("/create")
	    public String createRecipe(@ModelAttribute RecipeJSONDTO newRecipe, RedirectAttributes redirectAttributes) {
	        recipeJSONService.create(newRecipe);
	        redirectAttributes.addFlashAttribute("success", "Recipe created successfully.");
	        return "redirect:/index"; 
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
		return pagination(model,1);
//		return "start";
	}
//	
//	@RequestMapping(value="/", method=RequestMethod.GET)
//	public String homePage(Model model) {
//		return pagination(model,1);
//	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET, headers={"content-type=text/json"})
	@ResponseBody
	public UserDTO readUserJSON(Model model) {
		UserDTO userDTO = userServiceStub.fetchById(1010);
		model.addAttribute("userDTO", userDTO);
		return userDTO;
	}
	
	@RequestMapping (value="/start", method=RequestMethod.GET, headers= {"content-type=text/json"})
	@ResponseBody
	public RecipeDTO readJSON(Model model) {
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
	public String saveRecipe(RecipeDTO recipeDTO) 
	{
		recipeDTO.setRecipeId(12);
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
	
}


