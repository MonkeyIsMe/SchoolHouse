package com.csu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.csu.dao.UserDAO;
import com.csu.dao.impl.UserDAOImpl;
import com.csu.model.User;
import com.csu.utils.DealDate;
import com.csu.utils.MD5;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	
	public String AddUser() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_account = request.getParameter("user_account");
		String user_pwd =  request.getParameter("user_pwd");
		String user_name =  request.getParameter("user_name");
		String user_tele =  request.getParameter("user_tele");
		String user_flag =  request.getParameter("user_flag");
		
		int uf = Integer.valueOf(user_flag);
		
		//密码MD5加密
		String password = MD5.md5(user_pwd);
		
		//获取时间
		DealDate dd = new DealDate();
		String CreatTime = dd.getDate();
		
		boolean flag = false;
		
		//创建用户
		User user = new User();
		user.setUserAccount(user_account);
		user.setUserCreatTime(CreatTime);
		user.setUserPassword(password);
		user.setUserName(user_name);
		user.setUserPhone(user_tele);
		user.setUserFlag(uf);
		
		//加入用户
		UserDAO ud = new UserDAOImpl();
		flag = ud.add(user);
		
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		if(flag) {
			out.println("Success");
	        out.flush(); 
	        out.close(); 
			return null;
		}
		else {
			out.println("Fail");
			return ERROR;
		}
		
	}
	
	public String RegisterUser() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_account = request.getParameter("user_account");
		String user_pwd =  request.getParameter("user_password");
		String user_name =  request.getParameter("user_name");
		String user_tele =  request.getParameter("user_tele");
		String user_email =  request.getParameter("user_email");
		String code =  request.getParameter("code");
		
		HttpSession session  = request.getSession();
		String checkcode = (String)session.getAttribute("checkCode");
		
		
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		if(code.equals(checkcode) == false) {
			out.println("WrongCode");
			return null;
		}
		else {
			//密码MD5加密
			String password = MD5.md5(user_pwd);
			
			//获取时间
			DealDate dd = new DealDate();
			String CreatTime = dd.getDate();
			
			boolean flag = false;
			
			//创建用户
			User user = new User();
			user.setUserAccount(user_account);
			user.setUserCreatTime(CreatTime);
			user.setUserPassword(password);
			user.setUserName(user_name);
			user.setUserPhone(user_tele);
			user.setUserEmail(user_email);
			user.setUserFlag(-1);
			
			//加入用户
			UserDAO ud = new UserDAOImpl();
			flag = ud.add(user);
			
			
			if(flag) {
				out.println("Success");
		        out.flush(); 
		        out.close(); 
				return null;
			}
			else {
				out.println("Fail");
				return ERROR;
			}
		}
		
	}
	
	public String UpdateUser() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_id =  request.getParameter("userId");
		String user_pwd =  request.getParameter("user_pwd");
		String user_name =  request.getParameter("userName");
		String user_tele =  request.getParameter("userPhone");
		
		System.out.println(user_id + " " + user_name + " " + user_tele);
		int u_id = Integer.valueOf(user_id);
		
		//密码MD5加密
		String password = MD5.md5(user_pwd);
		
		//获取时间
		DealDate dd = new DealDate();
		String UpdateTime = dd.getDate();
		
		boolean flag = false;
		
		//查询用户
		UserDAO ud = new UserDAOImpl();
		User user = ud.query(u_id);
		
		user.setUserUpdateTime(UpdateTime);
		user.setUserPassword(password);
		user.setUserName(user_name);
		user.setUserPhone(user_tele);
		
		//更新用户
		flag = ud.update(user);
		
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		if(flag) {
			out.println("Success");
	        out.flush(); 
	        out.close(); 
			return null;
		}
		else {
			out.println("Fail");
			return ERROR;
		}
		
	}
	
	
	public String DeleteUser() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_id =  request.getParameter("user_id");
		
		int u_id = Integer.valueOf(user_id);
		
		
		boolean flag = false;
		
		//查询用户
		UserDAO ud = new UserDAOImpl();
		User user = ud.query(u_id);
		
		
		//删除用户
		flag = ud.delete(user);
		
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		if(flag) {
			out.println("Success");
	        out.flush(); 
	        out.close(); 
			return null;
		}
		else {
			out.println("Fail");
			return ERROR;
		}
		
	}
	
	public String AccountIsExit() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_account =  request.getParameter("user_account");
		System.out.println(user_account);
		UserDAO ud = new UserDAOImpl();
		List<User> list = ud.QueryByAccount(user_account);
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		System.out.println(list.size());
		if(list.size() == 0) {
			out.println("Success");
	        out.flush(); 
	        out.close(); 
			return null;
		}
		else {
			out.println("Fail");
			return null;
		}
	}
	
	public String UserToSchool() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_id =  request.getParameter("user_id");
		
		int u_id = Integer.valueOf(user_id);
		
		
		boolean flag = false;
		
		//查询用户
		UserDAO ud = new UserDAOImpl();
		User user = ud.query(u_id);
		
		user.setUserFlag(0);
		//删除用户
		flag = ud.update(user);
		
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		if(flag) {
			out.println("Success");
	        out.flush(); 
	        out.close(); 
			return null;
		}
		else {
			out.println("Fail");
			return ERROR;
		}
	}
	
	
	public String UserToEdu() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_id =  request.getParameter("user_id");
		
		int u_id = Integer.valueOf(user_id);
		
		
		boolean flag = false;
		
		//查询用户
		UserDAO ud = new UserDAOImpl();
		User user = ud.query(u_id);
		
		user.setUserFlag(1);
		//更新信息
		flag = ud.update(user);
		
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		if(flag) {
			out.println("Success");
	        out.flush(); 
	        out.close(); 
			return null;
		}
		else {
			out.println("Fail");
			return ERROR;
		}
	}
}
