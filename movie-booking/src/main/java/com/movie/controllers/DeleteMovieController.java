
package com.movie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.movie.services.MovieService;


@Controller
public class DeleteMovieController {

    @Autowired MovieService mservice;
    

    @GetMapping("/delmovie/{mid}")
	public String deletemovie(@PathVariable("mid") int mid,RedirectAttributes ra) {
		mservice.deleteMovie(mid);
		ra.addFlashAttribute("msg", "Movie deleted successfully");
		return "redirect:/movies";
	}
}
