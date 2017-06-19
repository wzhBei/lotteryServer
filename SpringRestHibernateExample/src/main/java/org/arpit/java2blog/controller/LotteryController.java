package org.arpit.java2blog.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.arpit.java2blog.model.InitlotterySixDataModel;
import org.arpit.java2blog.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LotteryController {

	@Autowired
	LotteryService lotteryService;

	

	@RequestMapping(value = "/generateLotterys", method = RequestMethod.GET, headers = "Accept=application/json")
	public void generateData() {
        System.out.print("");
		this.printNow();
		 StringBuffer buff = new StringBuffer();
		for (int i = 1; i < 44; i++) {
			if (i > 1) {
				buff.append(",");
			}
			String s = String.valueOf(i);
			buff.append(s);
        }
		
        ArrayList<String> result = new ArrayList<String>();
         
        long start = new Date().getTime();

        ArrayList<InitlotterySixDataModel> modelList = new ArrayList<InitlotterySixDataModel>();
        InitlotterySixData init = new InitlotterySixData();
        try {
			result = init.GenCom(buff.toString(), ",", 6);
			System.out.println(result.size());
			
			for (int i = 0; i < result.size(); i++) {
				
				String originString = result.get(i);
				String[] nbrAry = originString.split(",");
				int[] intArry = new int[nbrAry.length];
				String key = "";
				for (int j = 0; j < nbrAry.length; j++) {
					intArry[j] = Integer.parseInt(nbrAry[j]);
					key = key + nbrAry[j];
				}
				InitlotterySixDataModel model = new InitlotterySixDataModel(key,
						intArry,
						init.sumData(intArry),
						init.crsData(intArry), 
						init.oddData(intArry), 
						init.intData(intArry), 
						init.conData(intArry));
				modelList.add(model);
				if (i > 0 && (i % 1000 == 0 || result.size() - i <= 1000)) {
			    	lotteryService.addInitLotterys(modelList);
			    	modelList = new ArrayList<InitlotterySixDataModel>();
				}
				System.out.println(model.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        long end = new Date().getTime() - start;
        System.out.printf("-------End--------Total Seconds Is : %d-------", end);
	}
	 
	public void printNow() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 锟斤拷锟皆凤拷锟斤拷锟斤拷薷锟斤拷锟斤拷诟锟绞�
		String hehe = dateFormat.format(now);
		System.out.println(hehe);
	}

	@RequestMapping(value = "/getLotterys", method = RequestMethod.POST, headers = "Accept=application/json")
	public List<InitlotterySixDataModel> getLotteryNumbers(@RequestBody String body) {

		List<InitlotterySixDataModel> listOfLotterys = lotteryService.getAllLotterys(body);
		return listOfLotterys;
	}

}
