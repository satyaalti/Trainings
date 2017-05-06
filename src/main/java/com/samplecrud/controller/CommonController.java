package com.samplecrud.controller;

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
	

	public  Users getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
		String username = ""; 
		Users user = null;
		try {
			  if (!(auth instanceof AnonymousAuthenticationToken)) {  
				   UserDetails userDetail = (UserDetails) auth.getPrincipal();  
				   username = userDetail.getUsername();
				   
				   if(!username.isEmpty())
					   user = this.userService.findByUserName(username);
			  }  
		 }
		 catch(Exception e) {
			 e.printStackTrace();
			 System.out.println("Something went wrong");
		 }
		 
		return user;
	}
	
}
