package com.samplecrud.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import com.samplecrud.dao.UsersBalanceDAO;
import com.samplecrud.model.Users;
import com.samplecrud.model.UsersBalance;




public class UsersBalanceDAOImpl  implements UsersBalanceDAO{
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	@Override
	public void addAmount(UsersBalance ub) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(ub);
		System.out.println(ub);
		
	//logger.info("user saved successfully, user Details="+ub);
	}
	@Override
	public void withdrawAmount(UsersBalance ub){
		Session session = this.sessionFactory.getCurrentSession();
		session.save(ub);
		//logger.info("user saved successfully, user Details="+ub);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UsersBalance> listUsersBalance(int userid){
		 //int count=0;
		
		 Session session = this.sessionFactory.getCurrentSession();  
		 Criteria criteria = session.createCriteria(UsersBalance.class)
				 					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				 					.add(Restrictions.eq("userid", userid));
		 criteria.addOrder(Order.desc("date"));
		
		 return criteria.list();
	}
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public String getbalance(int userid) {
		String bal = "0";
		
		try {
			Session session = this.sessionFactory.getCurrentSession();  
			Criteria criteria = session.createCriteria(UsersBalance.class)
								.add(Restrictions.eq("userid", userid));
			criteria.setProjection(Projections
	                .projectionList()
	                .add(Projections.sqlProjection(
	                  "sum(addamount) - (sum(withdrawamount)+sum(withdrawfee)+sum(transferfee)) as total",
	                  new String[] { "total" },
	                  new Type[] { StandardBasicTypes.DOUBLE }), "total"));
			
			
			List list = criteria.list();
			System.out.println(list);
			bal = list.get(0).toString();
			System.out.println(list.get(0).toString());
		}
		catch (NullPointerException e) {
			System.out.println("No records found");
			
		}
		return bal;
	    
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getbankuserslist(int userid,int bankid,String banktype) {
		
		List<Users> result = new ArrayList<Users>();
		try{
			Session session = this.sessionFactory.openSession();  
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
			
		}
		return result;
	}

	@Override
	public int  rowCount(int userid) {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		String current_date = df.format(dateobj);
		System.out.println(current_date);
		
		Session session = this.sessionFactory.getCurrentSession();  
		Criteria criteria = session.createCriteria(UsersBalance.class)
							.add(Restrictions.eq("userid", userid))
							.add(Restrictions.sqlRestriction("DATE_FORMAT(date, '%Y-%m-%d') = ?", current_date, StringType.INSTANCE))
							.add(Restrictions.in("typeoftxn", new String[] { "W", "WT"}));
		
		criteria.setProjection(Projections.rowCount());
		 Long count = (Long) criteria.uniqueResult();
		 int cnt = count.intValue();
		 return cnt;
		 //Calendar c = Calendar.getInstance();
		// .add(Restrictions.between("date", c.getdate());							
		//add.(Restrictions.in("Typeoftxn", Arrays.asList("W","WT")));
		//Criteria rest1= Restrictions.eq("Typeoftxn", "W"), 
			//	Criteria rest2= Restrictions.eq(Typeoftxn, "WT"), 
				//		criteria.add(Restrictions.or(rest1, rest2));
		//.add(Restrictions.or(Restrictions.eq("Typeoftxn", "W"), Restrictions.eq("Typeoftxn","WT")));
		
	}
}
		
	

 






