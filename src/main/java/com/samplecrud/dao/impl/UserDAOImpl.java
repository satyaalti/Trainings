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
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addUser(Users  u) {
		try (CloseableSession session = new CloseableSession(this.sessionFactory.openSession())) {
			session.delegate().persist(u);
			logger.info("user saved successfully, user Details="+u);
		}
		catch(HibernateException hbe) {
			logger.info("Occured Exception while adding user");
			throw new ExceptionInInitializerError(hbe);
		} 
	}
	
	@Override
	public void updateUser(Users u) {
		try (CloseableSession session = new CloseableSession(this.sessionFactory.openSession())) {
			session.delegate().update(u);
			logger.info("User updated successfully, user Details="+u);
		}
		catch(HibernateException hbe) {
			logger.info("Occured Exception while updating user");
			throw new ExceptionInInitializerError(hbe);
		} 
	}
	
	@Override
	public List<Users> listUsers() {
		List<Users> usersList = new ArrayList<Users>();
		try (CloseableSession session = new CloseableSession(this.sessionFactory.openSession())) {
			usersList = session.delegate().createCriteria(Users.class)  
				      .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)  
				      .list();
			logger.info("Fetched User list Successfully");
		}
		catch(HibernateException hbe) {
			logger.info("Occured Exception while fetching User list");
			throw new ExceptionInInitializerError(hbe);
		} 
		return usersList;
	}
	
	@Override
	public Users getUserById(int id) {
		Users u = new Users();
		try (CloseableSession session = new CloseableSession(this.sessionFactory.openSession())) {
			u = (Users) session.delegate().load(Users.class, new Integer(id));
			logger.info("Successfully fetched user list by Id");
		}
		catch(HibernateException hbe) {
			logger.info("Exception occured while trying fetch user list by Id");
			throw new ExceptionInInitializerError(hbe);
		}
		return u;
	}

	@Override
	public void removeUser(int id) {
		try (CloseableSession session = new CloseableSession(this.sessionFactory.openSession())) {
			Users u = new Users();
			Session sessionO = session.delegate();
			u = (Users) sessionO.load(Users.class, new Integer(id));/////
			if(null != u){
				sessionO.delete(u);
				logger.info("User deleted successfully");
			}
		}
		catch(HibernateException hbe) {
			logger.info("Exception occured while deleting a user");
			throw new ExceptionInInitializerError(hbe);
		} 
	}
	
	@Override
	public Users findByUserName(String username) { 
		Users user = new Users();
		try (CloseableSession session = new CloseableSession(this.sessionFactory.openSession())) {
			Session sessionO = session.delegate();
			Transaction tx = null;
			tx = sessionO.getTransaction();  
			sessionO.beginTransaction(); 
			Criteria criteria = sessionO.createCriteria(Users.class)
						.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
						.add(Restrictions.eq("username", username));
			user = (Users) criteria.list().get(0);  
			tx.commit();  
			logger.info("Successfully fetched User by username");
			
		}
		catch(Exception e) {
			logger.info("Exception occured while trying to fetch User by username");
			throw new ExceptionInInitializerError(e);
		}
		return user;
	 }  
}