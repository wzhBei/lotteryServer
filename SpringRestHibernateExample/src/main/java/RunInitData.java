import java.util.ArrayList;

import org.arpit.java2blog.controller.InitlotterySixData;
import org.arpit.java2blog.dao.LotteryDAO;
import org.arpit.java2blog.model.InitlotterySixDataModel;

public class RunInitData {

	public static int rangeMin;
	public static int rangeMax;
	public static int choiceNum;
	
	public static void main(String[] args) {
		
		InitlotterySixData init = new InitlotterySixData();
		
		StringBuffer buff = new StringBuffer();
		for (int i = rangeMin; i < rangeMax; i++) 
		{
			if (i > 1) {
				buff.append(",");
			}
			String s = String.valueOf(i);
			buff.append(s);
       }
		
       ArrayList<String> result = new ArrayList<String>();
       ArrayList<InitlotterySixDataModel> modelList = new ArrayList<InitlotterySixDataModel>();
       try 
       {
    	   result = init.GenCom(buff.toString(), ",", choiceNum);
    	   for (int i = 0; i < result.size(); i++)
           {
           		if (i > 10) break;
           		String originString = result.get(i);
            	String[] nbrAry = originString.split(",");
            	int[] intArry = new int[nbrAry.length];
            	String key = "";
            	for (int j = 0; j < nbrAry.length; j++)
            	{
            		intArry[j] = Integer.parseInt(nbrAry[j]);
            		key = key + nbrAry[j];
            	}
            	InitlotterySixDataModel model = new InitlotterySixDataModel(key, intArry, init.sumData(intArry), init.crsData(intArry), init.oddData(intArry), init.intData(intArry), init.conData(intArry));
            	modelList.add(model);
           }
    	   LotteryDAO service = new LotteryDAO();
    	   service.addInitLotterys(modelList);
       } 
       catch (Exception e) 
       {
			// TODO Auto-generated catch block
			e.printStackTrace();
       }
	}

}
