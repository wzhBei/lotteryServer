package org.arpit.java2blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.arpit.java2blog.dao.LotteryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.arpit.java2blog.model.*;

import net.sf.json.JSON;
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
		
		String sumMinString = (String) map.get("sumMin");
		String sumMaxString = (String) map.get("sumMax");
		
		String sumMinString = (String) map.get("sumMin");
		String sumMaxString = (String) map.get("sumMax");
		
		String sumMinString = (String) map.get("sumMin");
		String sumMaxString = (String) map.get("sumMax");
		
		String sumMinString = (String) map.get("sumMin");
		String sumMaxString = (String) map.get("sumMax");
		
		return lotteryDao.getLotteryWithConditionString("");
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
