package com.movie.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movie.models.Movie;
import com.movie.services.MovieService;

@Controller

public class EditMovieController {


	@Autowired MovieService mservice;



    @GetMapping("/editmovie/{mid}")
	public String editmovie(@PathVariable("mid") int mid,Model model) {
		model.addAttribute("movie", mservice.findMovieDetails(mid));
		return "editmovie";
	}
	
	@PostMapping("/editmovie/{mid}")
	public String editmovieprocess(@PathVariable("mid") int mid,Movie movie,RedirectAttributes ra) {
		movie.setMid(mid);
		mservice.updateMovie(movie);
		ra.addFlashAttribute("msg", "Movie updated successfully..");
		return "redirect:/movies";
	}
    
}
