package com.tongansz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
		time = time + year + month + day;
		//System.out.println(time);
		return time;
	}
	
	public String getBeforeTime(int x) {
		String time = "";
		SimpleDateFormat fd = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		calendar.add(Calendar.HOUR, -x);
		time = fd.format(calendar.getTime());
		return time;
	}
	
	public String getNowTime() {
		String time = "";
		SimpleDateFormat fd = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		time = fd.format(calendar.getTime());
		return time;
	}
	
	public String SixEarly(String date) throws ParseException {
		String time = "";
		String to = "";
		to = date.substring(0,4) + "-";
		to =  to + date.substring(4,6) + "-";
		to = to + date.substring(7,8);
		//System.out.println(to);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(to);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.HOUR, -52000);
		time = sdf.format(cal.getTime());
		return time;
	}

	public String StringDate(String Date) {
		String time = "";
		time = Date.substring(0, 4);
		time = time + Date.substring(5, 7);
		time = time + Date.substring(8, 10);
		return time;
	}
	
	public boolean JudgeDate(String Date) {
		boolean flag = true;
		//System.out.println(Date);
		if(Date.length() == 0) return false;
		
		for(int i = 0; i < Date.length(); i ++) {
			char str = Date.charAt(i);
			if(str >= '0' && str <= '9') continue;
			else {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public String GetTrueDate(String Date) {
		
		String date = "";
		
		int FirstWord = 0;
		int SecondWord = 0;
		
		String FirstDate = "";
		String SecondDate = "";
		
		date =  Date.substring(0, 4);
		
		for(int i = 4; i < Date.length(); i ++) {
			char str = Date.charAt(i);
			if(str >= '0' && str <= '9') continue;
			else {
				FirstWord = i;
				break;
			}
		}

		for(int i = FirstWord+1; i < Date.length(); i ++) {
			char str = Date.charAt(i);
			if(str >= '0' && str <= '9') continue;
			else {
				SecondWord = i;
				break;
			}
		}
		
		for(int i = FirstWord+1 ;i < SecondWord; i++) {
			char str = Date.charAt(i);
			FirstDate = FirstDate + str;
		}
		
		if(FirstDate.length() <= 1) {
			FirstDate = "0"+ FirstDate;
		}
		
		for(int i = SecondWord+1 ;i < Date.length(); i++) {
			char str = Date.charAt(i);
			if(str >= '0' && str <= '9') {
				SecondDate = SecondDate + str;
			}
		}
		
		if(SecondDate.length() <= 1) {
			SecondDate = "0"+ SecondDate;
		}
		
		date = date + FirstDate;
		date = date + SecondDate;
		//System.out.println(" FirstWord = " + FirstWord +" SecondWord = " +SecondWord + " FirstDate = " + FirstDate +" SecondDate = " +SecondDate);
		return date;
	}
	
}

//0123456789ABCDEFGHI
//2019-01-12 14:29:25