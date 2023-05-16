package com.movie.controllers;

import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import com.movie.models.MovieInfoModel;
import com.movie.services.BookingService;

import com.movie.services.MovieService;
import com.movie.services.ShowsService;
import com.movie.services.TheatreService;

@Controller

public class SeatBookController {

	@Autowired MovieService mservice;
	@Autowired TheatreService tservice;
	@Autowired ShowsService sservice;
	@Autowired BookingService bservice;


	@Autowired ServletContext ctx;


    @PostMapping("/seatbook")
	public String seatbooking(MovieInfoModel info,Model model) {
		model.addAttribute("info", info);
		model.addAttribute("movie", mservice.findMovieDetails(info.getMid()));
		model.addAttribute("show", sservice.findShow(info.getSid()));
		model.addAttribute("theatre", tservice.findTheatre(info.getTid()));
		String seatstr=bservice.getBookedSeats(info.getMid(),info.getTid(),info.getSid(), info.getBdate());
		System.out.println(seatstr);
		List<String> seats=null; 
		if(seatstr !=null) seats=Arrays.asList(seatstr.split(","));
		model.addAttribute("seats", seats);
		model.addAttribute("rows", new String[] {"A", "B", "C", "D", "E", "F","G","H","I","J","K","L","M","N","O"});
		return "seatbook";
	}
    
}
