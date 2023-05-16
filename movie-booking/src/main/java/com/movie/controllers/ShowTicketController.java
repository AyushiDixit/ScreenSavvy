package com.movie.controllers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;


import com.movie.models.Bookings;
import com.movie.models.User;
import com.movie.services.BookingService;

@Controller

public class ShowTicketController {


    @Autowired HttpSession session;
    @Autowired BookingService bservice;
	@Autowired ServletContext ctx;



    @PostMapping("/showticket")
	public String showticket(String bid,Model model) {
		User user=(User)session.getAttribute("user");
		String uname=user.getUname();
		String userid=user.getUserid();
		Bookings bk=bservice.findBookingDetails(Integer.parseInt(bid));
				
		String message=String.format(mailcontent(), uname,uname,bk.getBid(),bk.getMovie().getMname(),
				bk.getTheatre().getTname(),bk.getShow().getShowname(),bk.getBdate(),
				bk.getNoseat(),bk.getSeats(),bk.getAmount());
		model.addAttribute("bk", bk);
		return "showticket";
	}
	
	@SuppressWarnings("resource")
	public String mailcontent() {
		InputStream inputStream;
		String result="";
		try {
			inputStream = new FileInputStream(ctx.getRealPath("/")+"tickettemplate.html");
			result = new BufferedReader(new InputStreamReader(inputStream))
					.lines().collect(Collectors.joining("\n"));		
		} catch (IOException e) {
			System.out.println("Error "+e.getMessage());
		}
        return result;
	}
    
}
