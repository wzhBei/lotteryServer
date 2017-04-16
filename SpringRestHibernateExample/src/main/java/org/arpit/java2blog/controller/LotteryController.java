package org.arpit.java2blog.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.arpit.java2blog.model.Country;
import org.arpit.java2blog.model.LotteryModel;
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
        GenCom(buff.toString(), ",", 6, result);
        long end = new Date().getTime() - start;
        ArrayList<LotteryModel> models = new ArrayList<LotteryModel>();
        for (int i=0; i<result.size(); ++i)
        {
        	String originString = result.get(i);
        	String[] nbrAry = originString.split(",");
        	LotteryModel newModel = new LotteryModel(originString,
        			Integer.valueOf(nbrAry[0]).intValue(), 
        			Integer.valueOf(nbrAry[1]).intValue(), 
        			Integer.valueOf(nbrAry[2]).intValue(), 
        			Integer.valueOf(nbrAry[3]).intValue(), 
        			Integer.valueOf(nbrAry[4]).intValue(), 
        			Integer.valueOf(nbrAry[5]).intValue(),
        			0);
        	models.add(newModel);
            System.out.println((i+1) + ": " + result.get(i));
        }
        // TODO:
    	lotteryService.addSumLotterys(models, 1, 10);

        System.out.printf("-------End--------Total Seconds Is : %d-------", end);
	}
	
	 public static void GenCom(String srcStr, String sep, int n, List<String> result)
	    {
	        String[] str_list = srcStr.split(sep);
	         
	        //ѡ��λ
	        int[] pos = new int[n];
	         
	        //ѡ������
	        if (str_list.length < n || str_list.length <=0 || n <= 0)
	        {
	            return;
	        }
	         
	        //��ʼ��ǰn��ѡ��λ
	        for(int i=0; i<n; i++)
	        {
	            pos[i]=i;
	        }
	         
	        //ѭ������
	        while(true)
	        {
	            //1.����ѡ������
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
	             
	            //2.��λ
	            //��ѡ��λ���ұ߿�ʼ��ѡ���һ���������Ƶ�λ�ý��н�λ
	             
	            boolean is_move = false;
	 
	            for (int i=n-1; i>=0; i--)
	            {
	                if (pos[i] < str_list.length-n+i)    //���Խ�λ
	                {
	                    pos[i]++;   //ѡλ����
	                     
	                    //�����ұߵ�ѡ��ȫ����λ
	                    for (int k=i+1; k<n; ++k)
	                    {
	                        pos[k] = pos[i] + k - i;
	                    }
	                    is_move = true;
	                     
	                    break;
	                }
	            }
	             
	            if (!is_move)   //û�гɹ���λ,��ͷ��
	            {
	                break;
	            }
	        }
	    }
	 
	public void printNow() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// ���Է�����޸����ڸ�ʽ
		String hehe = dateFormat.format(now);
		System.out.println(hehe);
	}

	@RequestMapping(value = "/getAllLotterys", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Country> getLotteryNumbers() {

		List<Country> listOfCountries = lotteryService.getAllLotterys();
		return listOfCountries;
	}

	@RequestMapping(value = "/addLottery", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addCountry(@RequestBody LotteryModel lotteryModel) {
		lotteryService.addLottery(lotteryModel);

	}
}
