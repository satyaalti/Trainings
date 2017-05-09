package com.samplecrud.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samplecrud.dao.UserDAO;
import com.samplecrud.model.Users;



@SuppressWarnings("unchecked")
public class UserDAOImpl implements UserDAO {
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	private SessionFactory sessionFactory;
	
	private Session session;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addUser(Users  u) {
		try {
			session = this.sessionFactory.openSession();
			session.persist(u);
			logger.info("user saved successfully, user Details="+u);
		}
		catch(HibernateException hbe) {
			hbe.printStackTrace();
		} 
		finally {
			if(session.isOpen()){
				session.flush();
				session.close();
			}
		}
	}
	
	@Override
	public void updateUser(Users u) {
		try {
			session = this.sessionFactory.openSession();
			session.update(u);
			logger.info("User updated successfully, user Details="+u);
		}
		catch(HibernateException hbe) {
			hbe.printStackTrace();
		} 
		finally {
			if(session.isOpen()){
				session.flush();
				session.close();
			}
		}
	}
	
	@Override
	public List<Users> listUsers() {
		
		List<Users> usersList = new ArrayList<Users>();
		try {
			session = this.sessionFactory.openSession();
			usersList = session.createCriteria(Users.class)  
				      .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)  
				      .list();
			
		}
		catch(HibernateException hbe) {
			hbe.printStackTrace();
		} 
		finally {
			if(session.isOpen()){
				session.flush();
				session.close();
			}
		}
		return usersList;
	}
	
	@Override
	public Users getUserById(int id) {
		Users u = new Users();
		Session session = this.sessionFactory.openSession();
		try {
			u = (Users) session.load(Users.class, new Integer(id));
		}
		catch(HibernateException hbe) {
			hbe.printStackTrace();
		} 
		return u;
	}

	@Override
	public void removeUser(int id) {
		Users u = new Users();
		try {
			session = this.sessionFactory.openSession();
			u = (Users) session.load(Users.class, new Integer(id));/////
			if(null != u){
				session.delete(u);
				logger.info("Person deleted successfully");
			}
		}
		catch(HibernateException hbe) {
			hbe.printStackTrace();
		} 
		finally {
			if(session.isOpen()){
				session.flush();
				session.close();
			}
		}
	}
	
	@Override
	public Users findByUserName(String username) {  
		Users user = null;
		try {
			session = this.sessionFactory.openSession();
			Transaction tx = null;
			tx = session.getTransaction();  
			session.beginTransaction(); 
			Criteria criteria = session.createCriteria(Users.class)
						.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
						.add(Restrictions.eq("username", username));
			user = (Users) criteria.list().get(0);  
			tx.commit();  
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session.isOpen()){
				session.flush();
				session.close();
			}
		}
		return user;  
	 }  
}