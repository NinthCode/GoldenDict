package com.paouke.gdp.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomUtil {
	
	public static String getRandomCode(int N){
		String base = "1234567890aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";//设置随机码基础字
		Random random = new Random();
		StringBuffer resCode = new StringBuffer();
		for(int i = 0;i < N;i++){//循环获取N位随机码
			int num = random.nextInt(base.length());
			resCode.append(base.charAt(num));
		}
		return resCode.toString();
	}
	
	public static String getTimeMixRandomCode(int randomCodeLength){
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String time=format.format(date); 
		return time + getRandomCode(randomCodeLength);
	}

	public static String getTimeMixRandomCodeMixDataMd5(int randomCodeLength, Object data){
		return MD5Util.md5(getTimeMixRandomCode(randomCodeLength) + data.toString());
	}

	public static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getTimeMixRandomCode(3));
	}

}
