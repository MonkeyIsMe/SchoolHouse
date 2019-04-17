package com.tongansz.test;

import org.junit.Test;

import com.tongansz.utils.ChangeHtml;

public class TestUtils {

	
	@Test
	public void DoHtml() {
		String str="<div></div>"; 
		ChangeHtml ch = new ChangeHtml();
		System.out.println(ch.htmlReplace(str));
	}
}
