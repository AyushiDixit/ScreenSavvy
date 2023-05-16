package com.movie.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.movie.models.Theatre;
import com.movie.services.TheatreService;

@Controller

public class AddTheatreController {


    @Autowired TheatreService tservice;

    @GetMapping("/theatres")
	public String theatreslist(Model model) {
		model.addAttribute("list", tservice.allTheatres());
		return "theatres";
	}
	
	@PostMapping("/theatres")
	public String saveTheatre(Theatre th, RedirectAttributes ra) {
		Theatre tt= tservice.saveTheatre(th);
		ra.addFlashAttribute("msg", "Theatre created successfully");
		return "redirect:/theatres";
	}

}
