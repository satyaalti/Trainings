package com.samplecrud.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.samplecrud.constant.Variables;
import com.samplecrud.model.UsersBalance;

@Controller
@RequestMapping("/admin")
public class AdminUserBalanceController extends CommonController {
	
	@RequestMapping("/userbalanceinfo/{userid}")
    public String addAmount(@PathVariable("userid") int userid, HttpServletRequest request, @ModelAttribute("users_balance") UsersBalance ub, Model model){
		
		try{
		      if(request.getParameter("addbalancebtn") != null && request.getParameter("addbalancebtn").equals("Submit")){
		    	  
		    	  ub.setTypeoftxn("M");
		    	  this.usersBalanceService.addAmount(ub);
		    	  return "redirect:/admin/userbalanceinfo/"+userid;
		      }
		      else if(request.getParameter("withdrawbalancebtn") != null &&  request.getParameter("withdrawbalancebtn").equals("Submit")) {
		    	    ub.setTypeoftxn("W");
		    	    int rowCount = this.usersBalanceService.rowCount(userid);

		    	    System.out.println(rowCount );
				    if(rowCount > Variables.WITHDRAW_COUNT) {
				    	  ub.setWithdrawfee(10);
					}
				    
				    this.usersBalanceService.withdrawAmount(ub);
				    return "redirect:/admin/userbalanceinfo/"+userid;
				    
		      }
		     
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		String errorMsg = "";
		List<UsersBalance>  usersBalancelist = new ArrayList<UsersBalance>(); 
		try{
			usersBalancelist = this.usersBalanceService.listUsersBalance(userid);
			model.addAttribute("usersBalancelist",usersBalancelist);
			//used to retrieve the info from db and display to jsp
			if(usersBalancelist.size() == 0)
				errorMsg = "No results found";
			else
			{
				model.addAttribute("bal",this.usersBalanceService.getbalance(userid));
			}
		}
	
		catch(Exception e){
			errorMsg = "connect to sql server";
		}
        model.addAttribute("user", this.userService.getUserById(userid));
        model.addAttribute("errorMsg", errorMsg);
         
        return "addinfo";
        
    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/usertransfer", method = {RequestMethod.POST, RequestMethod.GET})
	public String transfer(Model model,HttpServletRequest request) {
		String errormsg = "";
		
		if(request.getParameter("transfer") != null && request.getParameter("transfer").equals( "Submit")) {
			int fromuserid = Integer.parseInt(request.getParameter("fromaccount"));
			int touserid = Integer.parseInt(request.getParameter("toaccount"));
			double transferamount = Double.parseDouble(request.getParameter("transferamount"));
			double totalbal_fromuserid = Double.parseDouble(this.usersBalanceService.getbalance(fromuserid));
			String banktype=request.getParameter("banktype");
			UsersBalance ub1=new UsersBalance();//for every object cration need to call service to set values to db
			double transferfee= 0;
			double key=0;
			if(banktype.equals("DB")){
				Map<Integer, Double> aMap =Variables.aMap;
					Iterator it = aMap.entrySet().iterator();
			     while (it.hasNext()) {
			            Map.Entry pair = (Map.Entry)it.next();
			            // avoids a ConcurrentModificationException
			            key=Double.parseDouble(pair.getKey().toString());//convert obj to string then double
			            transferfee=Double.parseDouble(pair.getValue().toString());
			            if(transferamount<=key)
			            	break;
			     }
			     ub1.setTransferfee(transferfee);
			}   
			           
			double withdrawAmount = transferamount;
			int rowCount = this.usersBalanceService.rowCount(fromuserid);
			
			if(rowCount > Variables.WITHDRAW_COUNT) {
				withdrawAmount = transferamount + 10;
				ub1.setWithdrawfee(10);
			}
			
	        if(totalbal_fromuserid < transferamount) {
	        	errormsg = "You have insufficient balance";
	        }
	        else{
				ub1.setUserid(fromuserid);
				ub1.setWithdrawamount(withdrawAmount);
				ub1.setTypeoftxn("WT");
				this.usersBalanceService.withdrawAmount(ub1);
				
				UsersBalance ub=new UsersBalance();
				ub.setUserid(touserid);
				ub.setAddamount(transferamount);
				ub.setTypeoftxn("T");
				ub.setTransferid(fromuserid);
				this.usersBalanceService.addAmount(ub);
				return "redirect:/admin/userlist"; 
	        }
		}
		model.addAttribute("errormsg", errormsg);
		model.addAttribute("userlist",this.userService.listUsers());
		return "admintransfer";
	}
	
}
