package com.movie.controllers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.movie.services.CustomerService;
import com.movie.services.UserService;
import com.movie.models.Customer;
import com.movie.models.User;


@Controller

public class RegisterController {


    @Autowired HttpSession session;
	@Autowired UserService uservice;
	@Autowired CustomerService cservice;
	@Autowired ServletContext ctx;


    @GetMapping("/register")
	public String registerpage() {
		return "registration";
	}
	
	@PostMapping("/register")
	public String registerprocess(Customer cust,String pwd, RedirectAttributes ra) {
		Customer c=cservice.saveCustomer(cust);
		User user=new User();
		user.setPwd(pwd);
		user.setRole("Customer");
		user.setUname(cust.getUname());
		user.setUserid(cust.getEmail());
		user.setId(c.getId());
		uservice.saveUser(user);
		String message=String.format(mailcontent(), cust.getUname(),cust.getEmail(),pwd);

		ra.addFlashAttribute("msg", "User registered successfully");
		return "redirect:/login";
	}

    @SuppressWarnings("resource")
	public String mailcontent() {
		InputStream inputStream;
		String result="";
		try {
			inputStream = new FileInputStream(ctx.getRealPath("/")+"mailtemplate.html");
			result = new BufferedReader(new InputStreamReader(inputStream))
					.lines().collect(Collectors.joining("\n"));		
		} catch (IOException e) {
			System.out.println("Error "+e.getMessage());
		}
        return result;
	}
    
}
