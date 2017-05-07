package com.samplecrud.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.samplecrud.model.Users;
import com.samplecrud.service.BankService;
import com.samplecrud.service.UserService;
import com.samplecrud.service.UsersBalanceService;

public class CommonController {
	
	protected UserService userService;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	protected UsersBalanceService usersBalanceService;
	
	@Autowired(required=true)
	@Qualifier(value="usersBalanceService")
	public void setUsersBalanceService(UsersBalanceService usersBalanceService) {
		this.usersBalanceService = usersBalanceService;
	}
	
	protected BankService bankService;
	
	@Autowired(required=true)
	@Qualifier(value="bankService")
	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}
	

	public  void setLoggedInUser(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
		String username = ""; 
		Users user = null;
		try {
			  if (!(auth instanceof AnonymousAuthenticationToken)) {  
				   UserDetails userDetail = (UserDetails) auth.getPrincipal();  
				   username = userDetail.getUsername();
				   
				   if(!username.isEmpty()) {
					   user = this.userService.findByUserName(username);
					   request.getSession().setAttribute("LOGGED_IN_USER", user);
				   }
			  }  
		 }
		 catch(Exception e) {
			 e.printStackTrace();
			 System.out.println("Something went wrong");
		 }
	}
	
	public Users getLoggedInUser(HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("LOGGED_IN_USER");
		if(user == null) {
			this.setLoggedInUser(request);
			user = (Users) request.getSession().getAttribute("LOGGED_IN_USER");
		}
		return user;
	}
}
