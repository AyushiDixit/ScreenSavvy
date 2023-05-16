package com.movie.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movie.models.Movie;
import com.movie.services.MovieService;


@Controller
public class AddMovieController {

    @Autowired MovieService mservice;


    @GetMapping("/addmovie")
	public String addmovie() {
		return "addmovie";
	}

    @PostMapping("/addmovie")
	public String addmovieprocess(Movie movie,MultipartFile photo,MultipartFile bphoto,RedirectAttributes ra) {
		mservice.saveMovie(movie,photo,bphoto);
		ra.addFlashAttribute("msg", "Movie added successfully..");
		return "redirect:/movies";
	}
	

    
}
