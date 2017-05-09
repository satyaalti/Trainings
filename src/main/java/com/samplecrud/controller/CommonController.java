package com.samplecrud.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.samplecrud.model.UserRole;
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
					   
					   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

					   Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
					   boolean isAdmin = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
					   String role = "ROLE_USER";
					   if(isAdmin) 
						   role = "ROLE_ADMIN";
					   
					   request.getSession().setAttribute("LOGGED_IN_USER_ROLE", role);
				   }
			  }  
		 }
		 catch(Exception e) {
			 e.printStackTrace();
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
