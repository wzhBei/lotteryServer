package org.arpit.java2blog.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.arpit.java2blog.model.InitlotterySixDataModel;
import org.hibernate.Query;
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
	
	public List<InitlotterySixDataModel> getLotteryWithConditionString(String conditionString) {
		Session session = this.sessionFactory.getCurrentSession();
		String queryBase = "from InitlotterySixDataModel";
		String fullQueryBaseString = queryBase;
		if (conditionString.trim().length() > 0) {
			fullQueryBaseString += " Where " + conditionString + " ORDER BY RAND()";
		}
		if (conditionString.length() == 0) {
			return this.generateRandomModels(5);
		}
		//+ " ORDER BY RAND() LIMIT 5"
		System.out.println("queryString is " + fullQueryBaseString);	
		Query query = session.createQuery(fullQueryBaseString);
		query.setMaxResults(5);
		List<InitlotterySixDataModel> countryList = query.list();
		return generateRandomNumbers(5, countryList);
	}
	
	List<InitlotterySixDataModel> generateRandomModels(int count) {
		List<InitlotterySixDataModel> resultAry = new ArrayList<InitlotterySixDataModel>();
		while(resultAry.size() < count) {
			InitlotterySixDataModel newModel = InitlotterySixDataModel.generateRandomModel();
			boolean shouldContinue = false;
			for(InitlotterySixDataModel model : resultAry) {
				if( model.isEqualToModel(newModel)) {
					shouldContinue = true;
				}
			}
			if (shouldContinue) continue;
			resultAry.add(newModel);
		}
		return resultAry;
	}
	
	List<InitlotterySixDataModel> generateRandomNumbers(int count, List<InitlotterySixDataModel> originalAry) {
		if (originalAry.size() <= 5) return originalAry;
		List<InitlotterySixDataModel> resultAry = new ArrayList<InitlotterySixDataModel>();
		Random random = new Random();
		for(int i = 0; i < originalAry.size(); i++) {
			if (resultAry.size() >= 5) break;
			int randomNbr = random.nextInt(originalAry.size());
			InitlotterySixDataModel selectedModel = originalAry.get(randomNbr);
			if (resultAry.contains(selectedModel)) continue;
			resultAry.add(selectedModel);
			System.out.println(selectedModel.getId());
		}
		return resultAry;
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
