package com.movie.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.movie.models.BookingDTO;
import com.movie.models.Bookings;
import com.movie.models.User;
import com.movie.services.BookingService;
import com.movie.services.CustomerService;

import com.movie.services.MovieService;
import com.movie.services.ShowsService;
import com.movie.services.TheatreService;



@Controller
public class PaymentAndBookController {


    @Autowired HttpSession session;
	@Autowired MovieService mservice;
	@Autowired TheatreService tservice;
	@Autowired ShowsService sservice;
	@Autowired BookingService bservice;
	@Autowired CustomerService cservice;

	@Autowired ServletContext ctx;



    @PostMapping("/book")
	public String saveBooking(BookingDTO dto) {
		System.out.println(dto);
		Bookings bk=BookingDTO.toEntity(dto);
		User user=(User)session.getAttribute("user");
		System.out.println(user);
		bk.setAmount(dto.getAmount()*dto.getNoseat());
		bk.setCustomer(cservice.findById(user.getId()));
		bk.setMovie(mservice.findMovieDetails(dto.getMid()));
		bk.setShow(sservice.findShow(dto.getSid()));
		bk.setTheatre(tservice.findTheatre(dto.getTid()));
		int id=bservice.saveBooking(bk);
		return "redirect:/payment/"+id;
	}
	
	@GetMapping("/payment/{id}")
	public String paymentpage(@PathVariable("id") int id,Model model) {
		model.addAttribute("bk", bservice.findBookingDetails(id));
		return "payment";
	}
    
}
