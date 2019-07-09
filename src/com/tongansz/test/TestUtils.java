package com.tongansz.test;

import org.junit.Test;

import com.tongansz.utils.ChangeHtml;
import com.tongansz.utils.DealDate;

public class TestUtils {

	
	@Test
	public void DoHtml() {
		String str="<div></div>"; 
		ChangeHtml ch = new ChangeHtml();
		System.out.println(ch.htmlReplace(str));
	}
	
	@Test
	public void JudgeDate() {
		String str1 = "20190501";
		String str2 = "2015/01/01";
		DealDate dd = new DealDate();
		System.out.println(dd.JudgeDate(str1) + " " + dd.JudgeDate(str2));
	}
	
	@Test
	public void GetTrueDate() {
		DealDate dd = new DealDate();
		String str2 = "2015/1/1";
		String str1 = "2015年01月21日";
		String str3 = "2015/01/01";
		System.out.println(dd.GetTrueDate(str1) + " " + dd.GetTrueDate(str2) + " " + dd.GetTrueDate(str3));
	}
	

	
}
