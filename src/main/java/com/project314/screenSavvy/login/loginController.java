package com.project314.screenSavvy.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {
	
	private LoginValidation loginValidation; 
	
	
	public loginController(LoginValidation loginValidation) {
		super();
		this.loginValidation = loginValidation;
	}



	@RequestMapping(value="login", method=RequestMethod.GET)
	public String goToLogin() {
		

		return "login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String goToMainPage(@RequestParam String name, @RequestParam String password, ModelMap model) {
		
		if (loginValidation.validation(name, password)){
			model.put("name", name); 
			model.put("password", password);
			
			//Authentication 
			//user: admin 
			//password: password 

			return "mainPage";
		}
		
		model.put("errorMessage", "Invalid Credentials! Please try again");
		return "login";
		
	}
}
