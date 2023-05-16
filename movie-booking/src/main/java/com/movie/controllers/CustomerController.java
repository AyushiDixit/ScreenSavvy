package com.movie.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.movie.models.User;
import com.movie.services.BookingService;




@Controller
public class CustomerController {
	
	@Autowired HttpSession session;

	@Autowired BookingService bservice;


	@GetMapping("bhistory")
	public String bookinghistory(Model model) {
		User user=(User)session.getAttribute("user");		
		model.addAttribute("list", bservice.customerBookings(user.getId()));
		return "bhistory";
	}
	
	

}
