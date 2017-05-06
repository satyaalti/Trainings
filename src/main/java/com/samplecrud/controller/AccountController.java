package com.samplecrud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samplecrud.constant.Variables;
import com.samplecrud.model.Users;
import com.samplecrud.model.UsersBalance;

@Controller
@RequestMapping("/my")
public class AccountController extends CommonController {
	
	@RequestMapping("/account") 
	public  String myAccount(Model model, HttpServletRequest request, @ModelAttribute("users_balance") UsersBalance ub) {
		
		Users user = (Users) request.getSession().getAttribute("LOGGED_IN_USER");
		//System.out.println(user.toString());
		
		try{
		      if(request.getParameter("addbalancebtn") != null && request.getParameter("addbalancebtn").equals("Submit")){
		    	  
		    	  ub.setTypeoftxn("M");
		    	  this.usersBalanceService.addAmount(ub);
		    	  return "redirect:/my/account/";
		      }
		      else if(request.getParameter("withdrawbalancebtn") != null &&  request.getParameter("withdrawbalancebtn").equals("Submit")) {
		    	    ub.setTypeoftxn("W");
		    	    int rowCount = this.usersBalanceService.rowCount(user.getUserid());

		    	    System.out.println(rowCount );
				    if(rowCount > Variables.WITHDRAW_COUNT) {
				    	  ub.setWithdrawfee(10);
					}
				    
				    this.usersBalanceService.withdrawAmount(ub);
				    return "redirect:/my/account/";
				    
		      }
		     
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		String errorMsg = "";
		List<UsersBalance>  usersBalancelist = new ArrayList<UsersBalance>(); 
		try{
			usersBalancelist = this.usersBalanceService.listUsersBalance(user.getUserid());
			model.addAttribute("usersBalancelist",usersBalancelist);
			//used to retrieve the info from db and display to jsp
			if(usersBalancelist.size() == 0)
				errorMsg = "No results found";
			else
			{
				model.addAttribute("bal",this.usersBalanceService.getbalance(user.getUserid()));
			}
		}
	
		catch(Exception e){
			errorMsg = "connect to sql server";
		}
      model.addAttribute("errorMsg", errorMsg);
		String balance = this.usersBalanceService.getbalance(user.getUserid());
		
		model.addAttribute("user", user);
		model.addAttribute("balance", balance);
		return "account";
	}
	
	
	
}
