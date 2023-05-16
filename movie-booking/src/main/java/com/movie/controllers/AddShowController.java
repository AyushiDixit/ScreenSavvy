package com.movie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movie.models.TheatreShow;
import com.movie.services.ShowsService;

@Controller
public class AddShowController {

    @Autowired ShowsService sservice;

    @GetMapping("/shows")
	public String showslist(Model model) {
		model.addAttribute("list", sservice.allShows());
		return "shows";
	}
	
	@PostMapping("/shows")
	public String saveshows(TheatreShow ts, RedirectAttributes ra) {
		sservice.saveShow(ts);
		ra.addFlashAttribute("msg", "Show added successfully");
		return "redirect:/shows";
	}
    
}
