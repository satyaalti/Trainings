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
	
	@RequestMapping("/userlist") 
	public  String UsersList(Model model) {
		String errorMsg = "";
		List<Users>  userlist = new ArrayList<Users>();
		try{
			userlist = this.userService.listUsers();
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
		
		model.addAttribute("errorMsg",errorMsg);
		return "userlist";
	}
	
	@RequestMapping(value="/adduser", method = {RequestMethod.POST, RequestMethod.GET})
	public String addUsers(HttpServletRequest request, @ModelAttribute("users") Users u, Model model) {
		try{
		      if(request.getParameter("submit").equals("Submit")){
		    	  //new person, add it
		    	  this.userService.addUser(u);
		      }
		      else if(request.getParameter("submit").equals("Update")) {
		    	  this.userService.updateUser(u);
		      }
		      return "redirect:/admin/userlist";
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
	
}

