package com.samplecrud.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Restrictions;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samplecrud.dao.BankDAO;
import com.samplecrud.model.Bank;


public class BankDAOImpl implements BankDAO {
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bank>  listOfBanks() {
		List<Bank> banks = new ArrayList<Bank>();
		try (CloseableSession session = new CloseableSession(this.sessionFactory.openSession())) {
			banks = session.delegate().createCriteria(Bank.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			logger.info("Successfully fetched Bank list");
		}
		catch(Exception e) {
			logger.info("Exception occured while fetching Bank list");
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
		return banks;
	}

}
