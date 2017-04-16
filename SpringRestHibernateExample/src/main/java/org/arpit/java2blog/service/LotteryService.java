package org.arpit.java2blog.service;

import java.util.ArrayList;
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
	public void addSumLotterys(List<LotteryModel> allModels, int mixSum, int maxSum) {
		ArrayList<SumLotteryModel> valuableModels = new ArrayList<SumLotteryModel>(); 
		for(LotteryModel model : allModels) {
			if (model.getTotal() < mixSum || model.getTotal() > maxSum) {
				continue;
			}
			SumLotteryModel sumModel = (SumLotteryModel)model;
			valuableModels.add(sumModel);
		}
		lotteryDao.addSumLotterys(valuableModels);
	}
}
