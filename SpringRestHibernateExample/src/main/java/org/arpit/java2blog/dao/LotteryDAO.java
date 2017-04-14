package org.arpit.java2blog.dao;

import java.util.List;

import org.arpit.java2blog.model.Country;
import org.arpit.java2blog.model.LotteryModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		List<Country> countryList = session.createQuery("from LotteryModel").list();
		return countryList;
	}
	
	public LotteryModel addCountry(LotteryModel lotteryModel) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(lotteryModel);
		return lotteryModel;
	}
}
