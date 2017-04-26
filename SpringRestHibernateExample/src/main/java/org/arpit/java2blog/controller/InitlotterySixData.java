package org.arpit.java2blog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.arpit.java2blog.model.InitlotterySixDataModel;

public class InitlotterySixData {

	public InitlotterySixData()
	{
		
	}

	public ArrayList<String> GenCom(String srcStr, String sep, int n) throws Exception
    {
		ArrayList<String> result = new ArrayList<String>();
        String[] str_list = srcStr.split(sep);

        int[] pos = new int[n];

        if (str_list.length < n || str_list.length <=0 || n <= 0)
        {
            throw new Exception("parameter srcStr is invalid");
        }

        for(int i=0; i<n; i++)
        {
            pos[i]=i;
        }
         
        while(true)
        {
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
             
             
            boolean is_move = false;
 
            for (int i=n-1; i>=0; i--)
            {
                if (pos[i] < str_list.length-n+i)    
                {
                    pos[i]++;   
                     
                    
                    for (int k=i+1; k<n; ++k)
                    {
                        pos[k] = pos[i] + k - i;
                    }
                    is_move = true;
                     
                    break;
                }
            }
             
            if (!is_move)   
            {
                break;
            }
        }
		return result;
    }

	public int sumData(int[] value)
	{
		int returnSum = 0;
		for (int i : value)
		{
			returnSum = returnSum + i;
		}
		return returnSum;
	}
	
	public int crsData(int[] value)
	{
		int min, max;
		min = max = value[0];
		for(int i = 1; i < value.length; i++)
		{
			if (value[i] > max)
			{
				max = value[i];
			}
			else if (value[i] < min)
			{
				min = value[i];
			}
		}
		return max - min;
	}
	
	public int oddData(int[] value)
	{
		int returnOddSum = 0;
		for (int i : value)
		{
			if (i % 2 == 0)
			{
				returnOddSum ++;
			}
		}
		return returnOddSum;
	}
	
	public int intData(int[] value)
	{
		int maxInterval = value[1] - value[0];
		for(int i = 1; i < value.length - 1; i++)
		{
			int interval = value[i+1] - value[i];
			if (maxInterval < interval)
			{
				maxInterval = interval; 
			}
		}
		return maxInterval;
	}
	
	public int conData(int[] value)
	{
		int returnConSum = 0;
		for(int i = 0; i < value.length - 1; i++)
		{
			if (value[i+1] - value[i] == 1)
			{
				returnConSum ++;
			}
		}
		return returnConSum;
	}
}
