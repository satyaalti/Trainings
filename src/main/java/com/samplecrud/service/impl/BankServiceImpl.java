package com.samplecrud.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.samplecrud.dao.BankDAO;
import com.samplecrud.model.Bank;
import com.samplecrud.service.BankService;

public class BankServiceImpl  implements BankService{
	
	private BankDAO bankDAO;

	public void setBankDAO(BankDAO bankDAO) {
		this.bankDAO = bankDAO;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Bank> listOfBanks() {
		return this.bankDAO.listOfBanks();
	}
}
