package org.arpit.java2blog.dao;

import java.util.List;

import org.arpit.java2blog.model.Country;
import org.arpit.java2blog.model.InitlotterySixDataModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
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
		List<Country> countryList = session.createQuery("from InitlotterySixDataModel").list();
		return countryList;
	}
	
	public void addInitLotterys(final List<InitlotterySixDataModel> data) {  
       Session session = this.sessionFactory.openSession();
       Transaction tx = session.beginTransaction();
       int index = 0;
       for(InitlotterySixDataModel model : data) {
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

	public InitlotterySixDataModel addLottery(InitlotterySixDataModel initlotterySixDataModel) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(initlotterySixDataModel);
		return initlotterySixDataModel;
	}  
	
	
}
