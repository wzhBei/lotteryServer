package org.arpit.java2blog.dao;

import java.util.List;

import org.arpit.java2blog.model.Country;
import org.arpit.java2blog.model.LotteryModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class LotteryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public List<Country> getAllNumbers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Country> countryList = session.createQuery("from LotteryModel").list();
		return countryList;
	}
	
	public LotteryModel addLottery(LotteryModel lotteryModel) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(lotteryModel);
		return lotteryModel;
	}
	
	public void addLotterys(final List<LotteryModel> LotteryModels) {  
       Session session = this.sessionFactory.openSession();
       Transaction tx = session.beginTransaction();
       int index = 0;
       for(LotteryModel model : LotteryModels) {
    	   session.save(model);
    	   if (index % 100 == 0) {
    		   session.flush();
    		   session.clear();
    	   }
    	   index ++;
       }
  	 tx.commit();
	 session.close();
       
    }  
}
