package com.samplecrud.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
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
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(u);
		logger.info("user saved successfully, user Details="+u);
	}
	@Override
	public void updateUser(Users u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(u);
		logger.info("User updated successfully, user Details="+u);
	}
	
	@Override
	public List<Users> listUsers() {
		
		/*Session session = this.sessionFactory.openSession();
		try {
			List<User> usersList = session.createCriteria(User.class).list();
			return usersList;
		}
		catch(HibernateException hbe) {
			System.out.println("Something went wrong in save card");
			throw new ExceptionInInitializerError(hbe);
		} 
		finally {
			if(session.isOpen()){
				session.flush();
				session.close();
			}
		}*/
		
		 Session session = this.sessionFactory.getCurrentSession();  
		    return session.createCriteria(Users.class)  
		      .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)  
		      .list();
		
		
	}
	
	@Override
	public Users getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Users u = (Users) session.load(Users.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+u);
		return u;
	}

	@Override
	public void removeUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Users u = (Users) session.load(Users.class, new Integer(id));/////
		if(null != u){
			session.delete(u);
		}
		logger.info("Person deleted successfully, person details="+u);
	}
	
	@Override
	 public Users findByUserName(String username) {  
		
		Users user = null;
		try {
			Session session = this.sessionFactory.openSession(); 
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
		return user;  
	 }  
}