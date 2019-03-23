package com.csu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.csu.dao.UserDAO;
import com.csu.dao.impl.UserDAOImpl;
import com.csu.model.User;
import com.csu.utils.SendEmail;

public class ForgetPasswordAction {
	
	public String ForgetPassword() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_account = request.getParameter("user_account");
		String code = request.getParameter("code");
		
		
		HttpSession session  = request.getSession();
		String checkcode = (String)session.getAttribute("checkCode");
		
		UserDAO ud = new UserDAOImpl();
		List<User> list = ud.QueryByAccount(user_account);
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		if(list.size() == 0) {
			out.println("NoAccount");
			out.flush(); 
			out.close(); 
			return null;
		}
		else if(code.equals(checkcode) == false) {
			out.println("WorngCode");
			out.flush(); 
			out.close(); 
			return null;
		}
		else{
			User user = list.get(0);
			int uid = user.getUserId();
			SendEmail se = new SendEmail();
			se.ForgetPassword(uid);
			out.println("Success");
			out.flush(); 
			out.close(); 
			return null;
		}
	}
}
