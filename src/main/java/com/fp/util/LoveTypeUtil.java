package com.fp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoveTypeUtil {
	//获取结果
	public static String getResult(String tea) {
		String result = "";
		char[] x = tea.toCharArray();
		for (int i = 0; i < x.length; i++) {
			for (int j = i+1; j < x.length; j++) {
				if(x[i] == x[j]){
					result = x[i]+"";
				}
			}
		}
		return result;
		
	}
	//从[0,n)选出12个数字
	public static List<Integer> getTenData(int n){
		Random r = new Random();
		int i;
		List<Integer> list = new ArrayList<Integer>();
		while(list.size() < 12){
            i = r.nextInt(n);
            if(!list.contains(i)){
                list.add(i);
            }
	    }
		return list;
	}
}
