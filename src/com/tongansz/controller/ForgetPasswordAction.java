package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.tongansz.dao.SchoolUserDAO;
import com.tongansz.dao.UserDAO;
import com.tongansz.dao.impl.SchoolUserDAOImpl;
import com.tongansz.dao.impl.UserDAOImpl;
import com.tongansz.model.SchoolUser;
import com.tongansz.model.User;
import com.tongansz.utils.SendEmail;

public class ForgetPasswordAction {
	
	public String ForgetPassword() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_account = request.getParameter("user_account");
		String code = request.getParameter("code");
		
		
		HttpSession session  = request.getSession();
		String checkcode = (String)session.getAttribute("checkCode");
		
		SchoolUserDAO ud = new SchoolUserDAOImpl();
		List<SchoolUser> list = ud.QueryByAccount(user_account);
		
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
			SchoolUser user = list.get(0);
			int uid = user.getUserId();
			SendEmail se = new SendEmail();
			se.ForgetPassword(uid);
			out.println("Success");
			out.flush(); 
			out.close(); 
			return null;
		}
	}
	
	
	public String UserForgetPassword() throws IOException{
		
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
