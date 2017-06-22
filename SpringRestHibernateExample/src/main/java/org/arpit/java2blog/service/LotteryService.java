package org.arpit.java2blog.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.functors.ForClosure;
import org.arpit.java2blog.controller.InitlotterySixData;
import org.arpit.java2blog.dao.LotteryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.arpit.java2blog.model.*;
import org.hibernate.sql.Select;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;;

@Service("lotteryService")
public class LotteryService {

	@Autowired
	LotteryDAO lotteryDao;
	
	@Transactional
	public List<InitlotterySixDataModel> getAllLotterys(String body) {
		Map map = (Map)JSONObject.fromObject(body);
		
		String[] exludedNbrs;
		
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
		
		JSONArray luckNumberJson = (JSONArray)map.get("luckyNumbers");
		String luckCountMax = (String)map.get("luckMaxCount");
		String luckCountMin = (String)map.get("luckMinCount");
		if (luckNumberJson != null ) {
			Object[] objs = luckNumberJson.toArray();
			List<String> tempList = new ArrayList<String>();
			for(Object s : objs)
			{
				String s1 = s.toString();
				tempList.add(s1);
			}
			
			 Comparator c = new Comparator<String>() {  
					@Override
					public int compare(String o1, String o2) {
						int a = Integer.parseInt(o1);
						int b = Integer.parseInt(o2);
						if (a < b) {
							return -1; 
						} else {
							return 1;
						}
					}  
		        }; 
		        
			tempList.sort(c);
			String luckCondition = generateQueryWithLucknumbers(tempList, luckCountMin, luckCountMax);
			String coverdLuckCondition = "(" + luckCondition + ")";
			if (luckCondition.length() > 0) {
				if (queryString.length() > 0) {
					queryString += " AND ";
				}
				queryString += coverdLuckCondition;
			}
		}
		
		JSONArray excludedNumberJson = (JSONArray)map.get("excludedNbrs");
		if (excludedNumberJson != null) {
			Object[] objs2 = excludedNumberJson.toArray();
			String[] tempList2 = new String[objs2.length];
			int index = 0;
			for(Object s : objs2)
			{
				String s1 = s.toString().trim();
				tempList2[index] = s1;
				index ++;
			}
			
			int excludedCount = (int) map.get("excludedCount");
			String excludedCondition = generateQueryWithExcludedNumbers(tempList2, excludedCount);
			System.out.println(excludedCondition);
			
			if (excludedCondition.length() > 0) {
				if(queryString.length() > 0) {
					queryString += " and ";
				}
				queryString +=  "id not in (select id from InitlotterySixDataModel where " + excludedCondition + ")";
			}
		}
		
		return lotteryDao.getLotteryWithConditionString(queryString);
	}
	
	//
	String generateQueryWithExcludedNumbers(String[] src, int nbrCount) {
		String query = "";
		
		// 获得当前一组数字
		for(int currentNbrIndex = 0; currentNbrIndex < src.length; currentNbrIndex++) {
			String currentNbr = src[currentNbrIndex];
			String[] currentNbrs = currentNbr.split(",");
			if (currentNbrIndex > 0) {
				query += " OR ";
			}
			query += generateQueryForNbrs(currentNbrs, nbrCount);
		}
		return "(" + query + ")";
	}
	
	// nbr "1,5,11,15,19,30"
	String generateQueryForNbrs(String[] nbrs, int nbrCount) {
		String query = "";
		try {
			InitlotterySixData init = new InitlotterySixData();
			List<String> gemdNumbers = init.GenCom("1,2,3,4,5,6", ",", nbrCount);			
			// 循环所有索引
			for(int j = 0; j < gemdNumbers.size(); j++) {
				String indexString = gemdNumbers.get(j);
				String indexNbrs[] =  indexString.split(",");
			
				// 一对索引所生成的query
				String tempQuery = "";
				for (int k = 0; k < indexNbrs.length; k++) {
					if (k == 0) {
						tempQuery += "(";
					}
					if (k > 0) {
						tempQuery += " AND ";
					}
					String currentIndexNbr = indexNbrs[k];
					int intIndex = Integer.parseInt(currentIndexNbr);
					intIndex --;
					String currentIndexValue = nbrs[intIndex];
					tempQuery += "N"+currentIndexNbr+" = " +currentIndexValue;
					if (k == indexNbrs.length - 1) {
						tempQuery += ")";
					}
				}
				if (j > 0) {
					query += " OR ";
				}
				query += tempQuery;
			}
			return "(" + query + ")";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
}
	//
	
	
	// TODO: 优化
	String generateQueryWithLucknumbers(List<String> numbers, String luckCountMin, String luckCountMax) {
		int min = Integer.parseInt(luckCountMin);
		int max = Integer.parseInt(luckCountMax);
		String result = "";
		int count = 0;
		String integrateString = "";
		for(int i = 0; i < numbers.size(); i++) {
			if (i > 0) {
				integrateString += ",";
			}
			String str = numbers.get(i);
			integrateString += str;
		}
		
		for(int i = min; i <= max; i++ ) {			
			InitlotterySixData init = new InitlotterySixData();
			try {
				List<String> gemdNumbers = init.GenCom(integrateString, ",", i);
				int random = this.generaterRandomNbr(gemdNumbers.size());
				for(int j = 0; j < gemdNumbers.size(); j++) {
					if (j != random) {
						continue;
					}
					String str = gemdNumbers.get(j);
			        String[] strAry = str.split(",");
			        List<String> strList = Arrays.asList(strAry);
			        String queryForNumbers = generateQueryWithLucknumbers(strList);
			        if (count > 0) {
						result += " OR ";
					}
			        result += queryForNumbers;
			        count++;
			        break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/// 从输入的数字中随机取出一组，生成条件
	String generateQueryWithLucknumbers(List<String> numbers) {
		InitlotterySixData init = new InitlotterySixData();
		ArrayList<String> result = new ArrayList<String>();
		try {
			result = init.GenCom("1,2,3,4,5,6", ",", numbers.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String queryString = "";
		int random = this.generaterRandomNbr(result.size());
		int count = 0;
		for (int i = 0; i < result.size(); i++) {
			if (i != random) {
				continue;
			}
			String temp = result.get(i);
	        String[] str_list = temp.split(",");
	        if (count > 0) {
				queryString += " OR ";
			}
	        
	        String rsInOneLoop = "(";
	        for(int j = 0; j < str_list.length; j++) {
	        	String s = str_list[j];
	        	if (j > 0) {
	        		rsInOneLoop += " AND ";
				}
	        	String nbrValue = numbers.get(j);
	        	rsInOneLoop += "N" + s + " = " + nbrValue;
	        }
	        rsInOneLoop += ")";
	        
			queryString += rsInOneLoop;
			count ++;
			break;
		}
		
		return queryString;
	}
	
	@Transactional
	public void addLottery(InitlotterySixDataModel InitlotterySixDataModel) {
		lotteryDao.addLottery(InitlotterySixDataModel);
	}
	
	@Transactional
	public void addInitLotterys(List<InitlotterySixDataModel> allModels) {
		
		lotteryDao.addInitLotterys(allModels);
	}
	
	// 1 ~ max随机数
	int generaterRandomNbr(int max) {
		Random random = new Random();
		return random.nextInt(max);		
	}
	
}
