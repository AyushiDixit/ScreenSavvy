package com.movie.controllers;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.movie.models.User;
import com.movie.services.CustomerService;
import com.movie.services.MovieService;
import com.movie.services.ShowsService;
import com.movie.services.TheatreService;
import com.movie.services.UserService;


@Controller
public class LoginController {

    @Autowired HttpSession session;
	@Autowired UserService uservice;
	@Autowired MovieService mservice;
	@Autowired CustomerService cservice;
	@Autowired TheatreService tservice;
	@Autowired ShowsService sservice;

	@Autowired ServletContext ctx;

    @GetMapping("/login")
	public String loginpage() {
		return "login";
	}

    @PostMapping("/login")
	public String validate(String userid,String pwd) {
		User user=uservice.validate(userid, pwd);
		if(user!=null) {
			session.setAttribute("user", user);
			switch(user.getRole()) {
				case "Admin":
					return "redirect:/dashboard";
				case "Theatre":
					return "redirect:/tprofile";
				case "Customer":
					return "redirect:/";
			}
		}
		return "redirect:/login";
	}
    
}
