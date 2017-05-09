package com.samplecrud.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samplecrud.dao.UsersBalanceDAO;
import com.samplecrud.model.Users;
import com.samplecrud.model.UsersBalance;

public class UsersBalanceDAOImpl  implements UsersBalanceDAO{
	private static final Logger logger = LoggerFactory.getLogger(UsersBalanceDAOImpl.class);
	
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addAmount(UsersBalance ub) {
		Session session = this.sessionFactory.openSession();
		try {
			session.save(ub);
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
	public void withdrawAmount(UsersBalance ub){
		Session session = this.sessionFactory.openSession();
		try {
			session.save(ub);
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UsersBalance> listUsersBalance(int userid){
		 Session session = this.sessionFactory.openSession();
		 List<UsersBalance> ub = new ArrayList<UsersBalance>();
		 try {
			 Criteria criteria = session.createCriteria(UsersBalance.class)
					 					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					 					.add(Restrictions.eq("userid", userid));
			 criteria.addOrder(Order.desc("date"));
			 ub = criteria.list();
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
		return ub;
	}
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public String getbalance(int userid) {
		String bal = "0";
		Session session = this.sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(UsersBalance.class)
								.add(Restrictions.eq("userid", userid));
			criteria.setProjection(Projections
	                .projectionList()
	                .add(Projections.sqlProjection(
	                  "sum(addamount) - (sum(withdrawamount)+sum(withdrawfee)+sum(transferfee)) as total",
	                  new String[] { "total" },
	                  new Type[] { StandardBasicTypes.DOUBLE }), "total"));
			
			
			List list = criteria.list();
			bal = list.get(0).toString();
		}
		catch (NullPointerException e) {
			System.out.println("No records found");
			e.printStackTrace();
		}
		finally {
		 	if(session.isOpen()){
				session.flush();
				session.close();
			}
		}

		return bal;
	    
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getbankuserslist(int userid,int bankid,String banktype) {
		Session session = this.sessionFactory.openSession();
		List<Users> result = new ArrayList<Users>();
		try{
			Criteria criteria = session.createCriteria(Users.class);
			if(banktype.equals("SB"))
			{
				criteria.add(Restrictions.ne("userid", userid));				 
				criteria.add(Restrictions.eq("bankid", bankid));
			}
			else{
				criteria.add(Restrictions.ne("bankid", bankid));
						
			}
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			result = criteria.list();
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

		return result;
	}

	@Override
	public int  rowCount(int userid) {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		String current_date = df.format(dateobj);
		int cnt = 0;
		
		Session session = this.sessionFactory.openSession();
		
		try {
			Criteria criteria = session.createCriteria(UsersBalance.class)
								.add(Restrictions.eq("userid", userid))
								.add(Restrictions.sqlRestriction("DATE_FORMAT(date, '%Y-%m-%d') = ?", current_date, StringType.INSTANCE))
								.add(Restrictions.in("typeoftxn", new String[] { "W", "WT"}));
			
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();
			cnt = count.intValue();
			
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
		return cnt;
	}
}
		
	

 






