package com.samplecrud.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.samplecrud.dao.UsersBalanceDAO;
import com.samplecrud.model.Users;
import com.samplecrud.model.UsersBalance;
import com.samplecrud.service.UsersBalanceService;

public class UsersBalanceServiceImpl implements UsersBalanceService {
	
	private UsersBalanceDAO usersBalanceDAO;

	public void setUsersBalanceDAO(UsersBalanceDAO usersBalanceDAO) {
		this.usersBalanceDAO = usersBalanceDAO;
	}


	@Override
	@Transactional
	public void addAmount(UsersBalance ub) {
		this.usersBalanceDAO.addAmount(ub);
	}
	
	@Override
	@Transactional
	public void withdrawAmount(UsersBalance ub) {
		this.usersBalanceDAO.withdrawAmount(ub);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<UsersBalance> listUsersBalance(int userid){
		return this.usersBalanceDAO.listUsersBalance(userid);
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public String getbalance(int userid){
		 return this.usersBalanceDAO.getbalance(userid);
	}
	
	@Override
	@Transactional(readOnly=true)
	public int trnsactionsCountPerDay(int userid){
		 return this.usersBalanceDAO.trnsactionsCountPerDay(userid);
	}
	@Override
	@Transactional(readOnly=true)
	public List<Users> getbankuserslist(int userid,int bankid,String banktype){
		 return this.usersBalanceDAO.getbankuserslist(userid,bankid,banktype);
	}
		
	
	
}
