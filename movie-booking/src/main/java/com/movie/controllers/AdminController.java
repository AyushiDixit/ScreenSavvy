package com.movie.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.movie.services.BookingService;
import com.movie.services.CustomerService;
import com.movie.services.MovieService;
import com.movie.services.ShowsService;
import com.movie.services.TheatreService;
import com.movie.services.UserService;

@Controller
public class AdminController {
	
	@Autowired HttpSession session;
	@Autowired UserService uservice;
	@Autowired MovieService mservice;
	@Autowired TheatreService tservice;
	@Autowired ShowsService sservice;
	@Autowired BookingService bservice;
	@Autowired CustomerService cservice;

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("users", uservice.allUsers().size());
		model.addAttribute("movies", mservice.allMovies().size());
		model.addAttribute("theatres", tservice.allTheatres().size());
		model.addAttribute("bookings", bservice.allBookings().size());
		model.addAttribute("customers", cservice.allCustomers().size());
		
		return "dashboard";
	}
	
	@GetMapping("/users")
	public String userslist(Model model) {
		model.addAttribute("list", uservice.allUsers());
		return "users";
	}
	
	@GetMapping("/movies")
	public String movielist(Model model) {
		model.addAttribute("list", mservice.allMovies());
		return "movies";
	}
	
	@GetMapping("/atrailer/{id}")
	public String movietrailer(@PathVariable("id")int mid, Model model) {
		model.addAttribute("movie", mservice.findMovieDetails(mid));
		return "atrailer";
	}
	
	
	@GetMapping("/bookings")
	public String bookingslist(Model model) {
		model.addAttribute("list", bservice.allBookings());
		return "bookings";
	}
	
	@GetMapping("/madetails/{mid}")
	public String moviedetails(@PathVariable("mid") int mid,Model model) {
		model.addAttribute("movie", mservice.findMovieDetails(mid));
		return "madetails";
	}
	
	@GetMapping("/customers")
	public String customerslist(Model model) {
		model.addAttribute("list", cservice.allCustomers());
		return "customers";
	}
	
	@GetMapping("/changepwd")
	public String changepassword() {
		return "changepwd";
	}
}
