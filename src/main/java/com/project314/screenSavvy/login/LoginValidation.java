package com.project314.screenSavvy.login;

import org.springframework.stereotype.Service;

@Service
public class LoginValidation {
	public boolean validation(String username, String password) {
		
		boolean isValidUser = username.equalsIgnoreCase("admin"); 
		boolean isValidPassword = password.equalsIgnoreCase("password"); 
		
		return isValidUser && isValidPassword; 
	}
}
