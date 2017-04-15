package com.samplecrud.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Restrictions;

import com.samplecrud.dao.BankDAO;
import com.samplecrud.model.Bank;


public class BankDAOImpl implements BankDAO {
	
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bank>  listOfBanks() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Bank> banks = session.createCriteria(Bank.class)
				//.add(Restrictions.eq("bankid", bankid))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		System.out.println("bankdao"+ banks.toString());
		return banks;
		    
	}

}
