package com.samplecrud.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.samplecrud.model.Users;

@Controller  
public class LoginController extends CommonController {  

	@RequestMapping(value = "/" )  
	public String getUserDefault(HttpServletRequest request) { 
		this.setLoggedInUser(request);
		try {
			String role =  request.getSession().getAttribute("LOGGED_IN_USER_ROLE").toString();
			if(role.equals("ROLE_ADMIN"))
				return "redirect:/admin/userlist";
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/user/account";
	}  
	
	@RequestMapping(value = "/home" )  
	public String getHomeDefault() {  
		return "home";
	} 
	
	@RequestMapping("/login")  
	public String getLoginForm(@ModelAttribute Users user,  HttpServletRequest request, Model model) {
		String error = request.getParameter("error");
		String logout = request.getParameter("logout");
		String message = "";  
		if (error != null) {  
			message = "Incorrect username or password !";  
		} 
		else if (logout != null) {  
			request.getSession().invalidate();
			message = "Logout successful !";  
		}  
		model.addAttribute("message", message);
		return "login";  
	} 
	
	@RequestMapping("/admin")  
	public String getAdminProfile() {  
		return "redirect:/admin/userlist"; 
	} 
	
	@RequestMapping("/user")  
	public String getUserProfile() {  
		return "redirect:/user/account"; 
	}  
	
	@RequestMapping("/403")  
	public String getAccessDenied(Model model) {  
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
		String username = "";  
		try {
			if (!(auth instanceof AnonymousAuthenticationToken)) {  
				UserDetails userDetail = (UserDetails) auth.getPrincipal();  
				username = userDetail.getUsername();  
			}  
		}
		catch(Exception e) {
			System.out.println("Something went wrong");
		}
		model.addAttribute("username", username);
		return "403";
	}
	
	@RequestMapping(value="/register", method = {RequestMethod.POST, RequestMethod.GET})
	public String register(HttpServletRequest request, @ModelAttribute("users") Users u, Model model) {
		try{
			  if(request.getParameter("submit") != null && request.getParameter("submit").equals("Submit")) {
			      this.userService.addUser(u);
			      return "redirect:/login";
			  }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		model.addAttribute("bankslist",this.bankService.listOfBanks());
		return "register";
	}
}  
