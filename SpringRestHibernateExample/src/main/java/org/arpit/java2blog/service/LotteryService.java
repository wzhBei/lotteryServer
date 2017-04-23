package org.arpit.java2blog.service;

import java.util.ArrayList;
import java.util.List;

import org.arpit.java2blog.dao.LotteryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.arpit.java2blog.model.*;

@Service("lotteryService")
public class LotteryService {

	@Autowired
	LotteryDAO lotteryDao;
	
	@Transactional
	public List<Country> getAllLotterys() {
		return lotteryDao.getAllNumbers();
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
