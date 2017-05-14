package org.arpit.java2blog.service;

import java.util.List;
import java.util.Map;

import org.arpit.java2blog.dao.LotteryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.arpit.java2blog.model.*;

import net.sf.json.JSONObject;;

@Service("lotteryService")
public class LotteryService {

	@Autowired
	LotteryDAO lotteryDao;
	
	@Transactional
	public List<InitlotterySixDataModel> getAllLotterys(String body) {
		Map map = (Map)JSONObject.fromObject(body);
		
		String sumMinString = (String) map.get("sumMin");
		String sumMaxString = (String) map.get("sumMax");
		
		String crsMinString = (String) map.get("crsMin");
		String crsMaxString = (String) map.get("crsMax");
		
		String oddMinString = (String) map.get("oddMin");
		String oddMaxString = (String) map.get("oddMax");
		
		String intMinString = (String) map.get("intMin");
		String intMaxString = (String) map.get("intMax");
		
		String conMinString = (String) map.get("conMin");
		String conMaxString = (String) map.get("conMax");
		
		String n1Min = (String) map.get("MinN1");
		String n1Max = (String) map.get("MaxN1");
		
		String n2Min = (String) map.get("MinN2");
		String n2Max = (String) map.get("MaxN2");
		
		String n3Min = (String) map.get("MinN3");
		String n3Max = (String) map.get("MaxN3");
		
		String n4Min = (String) map.get("MinN4");
		String n4Max = (String) map.get("MaxN4");
		
		String n5Min = (String) map.get("MinN5");
		String n5Max = (String) map.get("MaxN5");
		
		String n6Min = (String) map.get("MinN6");
		String n6Max = (String) map.get("MaxN6");
		
		
		String sumQueryString = "SUMVALUE >= " + sumMinString + " AND " + " SUMVALUE <= " + sumMaxString;
		String crsQueryString = "CRSVALUE >= " + crsMinString + " AND " + " CRSVALUE <= " + crsMaxString;
		String oddQueryString = "ODDVALUE >= " + oddMinString + " AND " + " ODDVALUE <= " + oddMaxString;
		String intQueryString = "INTVALUE >= " + intMinString + " AND " + " INTVALUE <= " + intMaxString;
		String conQueryString = "CONVALUE >= " + conMinString + " AND " + " CONVALUE <= " + conMaxString;
		String n1QueryString = "N1 >= " + n1Min + " AND " + " N1 <= " + n1Max;
		String n2QueryString = "N2 >= " + n2Min + " AND " + " N2 <= " + n2Max;
		String n3QueryString = "N3 >= " + n3Min + " AND " + " N3 <= " + n3Max;
		String n4QueryString = "N4 >= " + n4Min + " AND " + " N4 <= " + n4Max;
		String n5QueryString = "N5 >= " + n5Min + " AND " + " N5 <= " + n5Max;
		String n6QueryString = "N6 >= " + n6Min + " AND " + " N6 <= " + n6Max;
		
		String[] ary = {sumQueryString, crsQueryString, oddQueryString, intQueryString, conQueryString, n1QueryString, n2QueryString, n3QueryString, n4QueryString, n5QueryString, n6QueryString};
		String queryString = "";
		int queryCount = 0;
		for (int i = 0; i < ary.length; i++) {
			String tempStr = ary[i];
			if (tempStr.contains("null")) continue;
			if (queryCount > 0) queryString += " AND ";
			queryString += tempStr;
			queryCount += 1;
			if (i == ary.length - 1) continue;
			
		}
		System.out.println("queryString is " + queryString);
		return lotteryDao.getLotteryWithConditionString(queryString);
	}
	
	@Transactional
	public void addLottery(InitlotterySixDataModel InitlotterySixDataModel) {
		lotteryDao.addLottery(InitlotterySixDataModel);
	}
	
	@Transactional
	public void addInitLotterys(List<InitlotterySixDataModel> allModels) {
		
		lotteryDao.addInitLotterys(allModels);
	}
}
