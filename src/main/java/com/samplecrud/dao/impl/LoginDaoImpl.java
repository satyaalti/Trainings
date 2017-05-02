package com.samplecrud.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.samplecrud.dao.LoginDao;
import com.samplecrud.model.Users;
import com.samplecrud.model.UsersBalance;

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
		  Criteria criteria = session.createCriteria(Users.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.eq("username", username));
		  Users user = (Users) criteria.list().get(0);  
		  tx.commit();  
		  return user;  
	 }  
}
