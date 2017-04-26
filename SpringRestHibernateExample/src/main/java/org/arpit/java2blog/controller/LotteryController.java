package org.arpit.java2blog.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.arpit.java2blog.model.Country;
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
//				System.out.println(model.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    	lotteryService.addInitLotterys(modelList);
        long end = new Date().getTime() - start;
        System.out.printf("-------End--------Total Seconds Is : %d-------", end);
	}
	
	 public void GenCom(String srcStr, String sep, int n, List<String> result)
	    {
	        String[] str_list = srcStr.split(sep);
	         
	        //选锟斤拷位
	        int[] pos = new int[n];
	         
	        //选锟斤拷锟斤拷锟斤拷
	        if (str_list.length < n || str_list.length <=0 || n <= 0)
	        {
	            return;
	        }
	         
	        //锟斤拷始锟斤拷前n锟斤拷选锟斤拷位
	        for(int i=0; i<n; i++)
	        {
	            pos[i]=i;
	        }
	         
	        //循锟斤拷锟斤拷锟斤拷
	        while(true)
	        {
	            //1.锟斤拷锟斤拷选锟斤拷锟斤拷锟斤拷
	            StringBuffer buff = new StringBuffer();
	            for(int i=0; i<n; i++)
	            {
	                if (i > 0)
	                {
	                    buff.append(sep);
	                }
	                 
	                buff.append(str_list[pos[i]]);
	            }
	             
	            result.add(buff.toString());
	             
	            //2.锟斤拷位
	            //锟斤拷选锟斤拷位锟斤拷锟揭边匡拷始锟斤拷选锟斤拷锟揭伙拷锟斤拷锟斤拷锟斤拷锟斤拷频锟轿伙拷媒锟斤拷薪锟轿�
	             
	            boolean is_move = false;
	 
	            for (int i=n-1; i>=0; i--)
	            {
	                if (pos[i] < str_list.length-n+i)    //锟斤拷锟皆斤拷位
	                {
	                    pos[i]++;   //选位锟斤拷锟斤拷
	                     
	                    //锟斤拷锟斤拷锟揭边碉拷选锟斤拷全锟斤拷锟斤拷位
	                    for (int k=i+1; k<n; ++k)
	                    {
	                        pos[k] = pos[i] + k - i;
	                    }
	                    is_move = true;
	                     
	                    break;
	                }
	            }
	             
	            if (!is_move)   //没锟叫成癸拷锟斤拷位,锟斤拷头锟斤拷
	            {
	                break;
	            }
	        }
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
