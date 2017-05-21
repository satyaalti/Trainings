package com.samplecrud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.samplecrud.model.Bank;
import com.samplecrud.model.Users;

@Controller
@RequestMapping("/admin")
public class AdminUserController extends CommonController {
	
	@RequestMapping(value = "/userlist/{enable}") 
	public  String usersListByStatus(Model model, HttpServletRequest request, @PathVariable String enable) {
		try{
			boolean status = true;
			if(enable != null && enable.equals("inactive"))
				status = false;
			this.userListCommon(model, request, status);			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return "userlist";
	}
	
	@RequestMapping(value = "/userlist") 
	public  String defaultUsersList(Model model, HttpServletRequest request) {
		this.userListCommon(model, request, true);
		return "userlist";
	}
	
	public void userListCommon(Model model, HttpServletRequest request, boolean enable) {
		String errorMsg = "";
		List<Users>  userlist = new ArrayList<Users>();
		try{
			userlist = this.userService.listUsers(enable);
			model.addAttribute("userlist",userlist);//used to retrieve the info from db and display to jsp
			if(userlist.size() == 0)
				errorMsg = "No results found";
		}
		catch(ObjectNotFoundException e){
			errorMsg = "User's are not been assigned to the banks";
		}
		catch(Exception e){
			errorMsg = "connect to sql server";
		}
		model.addAttribute("enable", enable);
		model.addAttribute("loggedInUser", this.getLoggedInUser(request));
		model.addAttribute("errorMsg",errorMsg);
	}
	
	@RequestMapping(value="/adduser", method = {RequestMethod.POST, RequestMethod.GET})
	public String addUsers(HttpServletRequest request, @ModelAttribute("users") Users u, Model model) {
		try{
			  if(request.getParameter("submit") != null) {
			      if( request.getParameter("submit").equals("Submit")){
			    	  //new person, add itrequest.getParameter("submit") != null &&
			    	  this.userService.addUser(u);
			      }
			      else if(request.getParameter("submit") != null && request.getParameter("submit").equals("Update")) {
			    	  this.userService.updateUser(u);
			      }
			      return "redirect:/admin/userlist";
			  }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		model.addAttribute("bankslist",this.bankService.listOfBanks());
		return "adduser";
	}
	
	@RequestMapping("/edituser/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
		
		Users user = this.userService.getUserById(id);
		//System.out.println(user.toString());
        model.addAttribute("user", user);
        List<Bank> bankslist = new ArrayList<Bank>();
		bankslist = this.bankService.listOfBanks();
		model.addAttribute("bankslist",bankslist);
        return "adduser";
    }
	
	@RequestMapping(value="/removeuser/{id}" ,method = {RequestMethod.POST,RequestMethod.GET})
	public String removeUser(@PathVariable("id") int id){
	    this.userService.removeUser(id);
        return "redirect:/admin/userlist";
    }
	
	@RequestMapping(value="/changeuserstatus" , method=RequestMethod.POST)
	public String changeUserStatus(HttpServletRequest request){
		Users user = this.userService.getUserById(Integer.parseInt(request.getParameter("selectedid")));
		System.out.println("enabled:"+request.getParameter("enable"));
		user.setEnabled(!Boolean.parseBoolean(request.getParameter("enable")));
		this.userService.changeUserStatus(user);
        return "redirect:/admin/userlist";
    }
	
}


