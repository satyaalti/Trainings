package com.samplecrud.service;

import java.util.List;

import com.samplecrud.model.Users;
import com.samplecrud.model.UsersBalance;



public interface UsersBalanceService {
	
	public void addAmount(UsersBalance ub);
	public void withdrawAmount(UsersBalance ub);
	public List<UsersBalance> listUsersBalance(int userid) ;
	public String getbalance(int userid);
	public int rowCount(int userid);
	public List<Users> getbankuserslist(int userid,int bankid,String banktype);
}
