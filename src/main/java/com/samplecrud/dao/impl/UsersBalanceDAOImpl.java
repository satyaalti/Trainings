package com.samplecrud.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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
		try (CloseableSession session = new CloseableSession(this.sessionFactory.openSession())) {
			session.delegate().save(ub);
			logger.info("Add Amount saved successfully");
		}
		catch(HibernateException hbe) {
			logger.info("Exception occured while adding Amount");
			throw new ExceptionInInitializerError(hbe);
		} 
	}
	
	@Override
	public void withdrawAmount(UsersBalance ub){
		try (CloseableSession session = new CloseableSession(this.sessionFactory.openSession())) {
			session.delegate().save(ub);
			logger.info("Withdraw Amount saved successfully");
		}
		catch(HibernateException hbe) {
			logger.info("Exception occured while withdraw Amount saved successfully");
			throw new ExceptionInInitializerError(hbe);
		} 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UsersBalance> listUsersBalance(int userid){
		List<UsersBalance> ub = new ArrayList<UsersBalance>();
		try (CloseableSession session = new CloseableSession(this.sessionFactory.openSession())) {
			 Criteria criteria = session.delegate().createCriteria(UsersBalance.class)
					 					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					 					.add(Restrictions.eq("userid", userid));
			 criteria.addOrder(Order.desc("date"));
			 ub = criteria.list();
			 logger.info("Success fully fetched User Balance list");
		}
		catch(HibernateException hbe) {
			 logger.info("Exception occured while fetching User Balance list");
			 throw new ExceptionInInitializerError(hbe);
		} 
		return ub;
	}
	
	
	@Override
	public String getbalance(int userid) {
		String bal = "0";
		try (CloseableSession session = new CloseableSession(this.sessionFactory.openSession())) {
			Criteria criteria = session.delegate().createCriteria(UsersBalance.class)
								.add(Restrictions.eq("userid", userid));
			criteria.setProjection(Projections
	                .projectionList()
	                .add(Projections.sqlProjection(
	                  "sum(addamount) - (sum(withdrawamount)+sum(withdrawfee)+sum(transferfee)) as total",
	                  new String[] { "total" },
	                  new Type[] { StandardBasicTypes.DOUBLE }), "total"));
			
			bal = criteria.list().get(0).toString();
			logger.info("Successfully Fetched User Balance");
		}
		catch (Exception e) {
			logger.info("Exception occured while fetching User Balance");
			//throw new ExceptionInInitializerError(e);
		}
		return bal;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getbankuserslist(int userid,int bankid,String banktype) {
		List<Users> us = new ArrayList<Users>();
		try (CloseableSession session = new CloseableSession(this.sessionFactory.openSession())) {
			Criteria criteria = session.delegate().createCriteria(Users.class);
			if(banktype.equals("SB"))
			{
				criteria.add(Restrictions.ne("userid", userid));				 
				criteria.add(Restrictions.eq("bankid", bankid));
			}
			else{
				criteria.add(Restrictions.ne("bankid", bankid));
			}
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			us = criteria.list();
			logger.info("Success fully fetched uses as per bank id");
		}
		catch(Exception e) {
			logger.info("Exception occured while fetching User list as per the bank id");
			throw new ExceptionInInitializerError(e);
		}
		return us;
	}

	@Override
	public int  trnsactionsCountPerDay(int userid) {
		int cnt = 0;
		try (CloseableSession session = new CloseableSession(this.sessionFactory.openSession())) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dateobj = new Date();
			String current_date = df.format(dateobj);
			Criteria criteria = session.delegate().createCriteria(UsersBalance.class)
								.add(Restrictions.eq("userid", userid))
								.add(Restrictions.sqlRestriction("DATE_FORMAT(date, '%Y-%m-%d') = ?", current_date, StringType.INSTANCE))
								.add(Restrictions.in("typeoftxn", new String[] { "W", "WT"}));
			
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();
			cnt = count.intValue();
			logger.info("Successfully Fetched transactions count per day");
		}
		catch(Exception e) {
			logger.info("Exception occured while fetching transactions count per day");
			throw new ExceptionInInitializerError(e);
		}
		return cnt;
	}
}