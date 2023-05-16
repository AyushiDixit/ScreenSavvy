package com.movie.controllers;



import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.movie.services.CustomerService;
import com.movie.services.MovieService;
import com.movie.services.ShowsService;
import com.movie.services.TheatreService;
import com.movie.services.UserService;


@Controller
public class HomeController {

	@Autowired HttpSession session;
	@Autowired UserService uservice;
	@Autowired MovieService mservice;
	@Autowired CustomerService cservice;
	@Autowired TheatreService tservice;
	@Autowired ShowsService sservice;

	@Autowired ServletContext ctx;
	
	@GetMapping("/")
	public String homepage(Model model) {
		model.addAttribute("list", mservice.allMovies());
		return "index";
	}
	
	@GetMapping("/trailer/{id}")
	public String movietrailer(@PathVariable("id")int mid, Model model) {
		model.addAttribute("movie", mservice.findMovieDetails(mid));
		return "trailer";
	}
	
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/contact")
	public String contactpage() {
		return "contact";
	}
	
	@GetMapping("/movielist")
	public String moviespage(Model model) {
		model.addAttribute("list", mservice.allMovies());
		return "movielist";
	}
	
	@GetMapping("/mdetails/{mid}")
	public String moviedetails(@PathVariable("mid") int mid,Model model) {
		model.addAttribute("movie", mservice.findMovieDetails(mid));
		model.addAttribute("theatres", tservice.allTheatres());
		model.addAttribute("shows", sservice.allShows());
		return "mdetails";
	}
	
}
