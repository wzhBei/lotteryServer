package org.arpit.java2blog.service;

import java.util.List;

import org.arpit.java2blog.dao.CountryDAO;
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
	public void addLottery(LotteryModel lotteryModel) {
		lotteryDao.addLottery(lotteryModel);
	}
	
	@Transactional
	public void addLotterys(List<LotteryModel> lotteryModels) {
		lotteryDao.addLotterys(lotteryModels);
	}
	
}
