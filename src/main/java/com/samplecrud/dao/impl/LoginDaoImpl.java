package com.samplecrud.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.samplecrud.dao.LoginDao;
import com.samplecrud.model.Users;

public class LoginDaoImpl implements LoginDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	Session session = null;  
	Transaction tx = null;  
	
	 @Override  
	 public Users findByUserName(String username) {  
		  session = sessionFactory.openSession();  
		  tx = session.getTransaction();  
		  session.beginTransaction();  
		  Users user = (Users) session.load(Users.class, new String(username));  
		  tx.commit();  
		  return user;  
	 }  
}
