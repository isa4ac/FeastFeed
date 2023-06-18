package com.feastfeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feastfeed.dto.RecipeDTO;
import com.feastfeed.dto.UserDTO;
import com.feastfeed.service.IRecipeService;
import com.feastfeed.service.IUserService;

@Controller
public class FeastfeedController {
	
	@Autowired
	private IRecipeService recipeServiceStub;
	
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
	
	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String readRecipe(Model model) {
		RecipeDTO recipeDTO = recipeServiceStub.fetchById(1);
		model.addAttribute("recipeDTO", recipeDTO);
		return "start";
	}
	
}


