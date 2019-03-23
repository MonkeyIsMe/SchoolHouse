package com.csu.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DealDate {
	
	public String getDate() {
		Calendar now = Calendar.getInstance();
		String time= "";
		String year =  now.get(Calendar.YEAR)+"";
		String month = (now.get(Calendar.MONTH) + 1) + "";
		if(month.length() != 2)  month = 0 + month;
		String day = now.get(Calendar.DAY_OF_MONTH) +"";
		if(day.length() != 2)  day = 0 + day;
		String hour = now.get(Calendar.HOUR_OF_DAY) +"";
		if(hour.length() != 2)  hour = 0 + hour;
		String min = now.get(Calendar.MINUTE)+"";
		if(min.length() != 2)  min = 0 + min;
		time = time + year + month + day + hour + min;
		//System.out.println(time);
		return time;
	}
	
	public String getBeforeTime(int x) {
		String time = "";
		SimpleDateFormat fd = new SimpleDateFormat("yyyyMMddHHmm");
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		calendar.add(Calendar.HOUR, -x);
		time = fd.format(calendar.getTime());
		return time;
	}
	
	public String getNowTime() {
		String time = "";
		SimpleDateFormat fd = new SimpleDateFormat("yyyyMMddHHmm");
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		time = fd.format(calendar.getTime());
		return time;
	}
	
	
}

//0123456789ABCDEFGHI
//2019-01-12 14:29:25