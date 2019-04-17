package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.SchoolDAO;
import com.tongansz.dao.SchoolUserDAO;
import com.tongansz.dao.impl.SchoolDAOImpl;
import com.tongansz.dao.impl.SchoolUserDAOImpl;
import com.tongansz.model.School;
import com.tongansz.model.SchoolUser;
import com.tongansz.utils.DealDate;
import com.tongansz.utils.MD5;

public class SchoolUserAction extends ActionSupport{
	
	public String AddSchoolUser() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_account = request.getParameter("user_account");
		String user_pwd =  request.getParameter("user_pwd");
		String user_name =  request.getParameter("user_name");
		String user_tele =  request.getParameter("user_tele");
		String user_flag =  request.getParameter("user_flag");
		String school_id = request.getParameter("school_id");
		String user_email =  request.getParameter("user_email");
		
		
		int uf = Integer.valueOf(user_flag);
		int sid = Integer.valueOf(school_id);
		//密码MD5加密
		String password = MD5.md5(user_pwd);
		
		//获取时间
		DealDate dd = new DealDate();
		String CreatTime = dd.getDate();
		
		boolean flag = false;
		
		SchoolDAO sd = new SchoolDAOImpl();
		School school = sd.query(sid);
		String SchoolName = school.getSchoolName();
		
		//创建用户
		SchoolUser user = new SchoolUser();
		user.setUserPasswords(user_pwd);
		user.setUserAccount(user_account);
		user.setUserCreatTime(CreatTime);
		user.setUserPassword(password);
		user.setUserName(user_name);
		user.setUserPhone(user_tele);
		user.setUserFlag(uf);
		user.setSchoolId(sid);
		user.setSchoolName(SchoolName);
		user.setUserEmail(user_email);
		
		//加入用户
		SchoolUserDAO ud = new SchoolUserDAOImpl();
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
			return null;
		}
		
	}
	
	public String RegisterSchoolUser() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_account = request.getParameter("user_account");
		String user_pwd =  request.getParameter("user_password");
		String user_name =  request.getParameter("user_name");
		String user_tele =  request.getParameter("user_tele");
		String user_email =  request.getParameter("user_email");
		String schoolid =  request.getParameter("schoolid");
		String code =  request.getParameter("code");
		HttpSession session  = request.getSession();
		String checkcode = (String)session.getAttribute("checkCode");
		
		
		SchoolDAO sd = new SchoolDAOImpl();
		List<School> schoollist = sd.getSchoolByName(schoolid);
		School school = schoollist.get(0);
		int sid = school.getSchoolId();
		
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
			SchoolUser user = new SchoolUser();
			user.setUserAccount(user_account);
			user.setUserCreatTime(CreatTime);
			user.setUserPassword(password);
			user.setUserName(user_name);
			user.setUserPhone(user_tele);
			user.setUserEmail(user_email);
			user.setSchoolId(sid);
			user.setSchoolName(schoolid);
			user.setUserFlag(-1);
			user.setUserPasswords(user_pwd);
			
			//加入用户
			SchoolUserDAO ud = new SchoolUserDAOImpl();
			flag = ud.add(user);
			
			
			if(flag) {
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
		
	}
	
	public String UpdateSchoolUser() throws IOException{
		//UpdateScholUserInfo
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_id =  request.getParameter("userId");
		String user_pwd =  request.getParameter("user_pwd");
		String user_name =  request.getParameter("userName");
		String user_tele =  request.getParameter("userPhone");
		String school_id = request.getParameter("school_id");
		
		
		//System.out.println(user_id + " " + user_name + " " + user_tele);
		int u_id = Integer.valueOf(user_id);
		int sid = Integer.valueOf(school_id);
		
		//密码MD5加密
		String password = MD5.md5(user_pwd);
		
		//获取时间
		DealDate dd = new DealDate();
		String UpdateTime = dd.getDate();
		
		boolean flag = false;
		
		//查询用户
		SchoolUserDAO ud = new SchoolUserDAOImpl();
		SchoolUser user = ud.query(u_id);
		
		SchoolDAO sd = new SchoolDAOImpl();
		School school = sd.query(sid);
		String SchoolName = school.getSchoolName();
		
		user.setUserUpdateTime(UpdateTime);
		user.setUserPassword(password);
		user.setUserName(user_name);
		user.setUserPhone(user_tele);
		user.setSchoolName(SchoolName);
		user.setUserPasswords(user_pwd);
		
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
			return null;
		}
		
	}
	
	public String UpdateSchoolUserInfo() throws IOException{
		//UpdateScholUserInfo
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_id =  request.getParameter("user_id");
		String user_pwd =  request.getParameter("user_password");
		String user_name =  request.getParameter("user_name");
		String user_tele =  request.getParameter("user_phone");
		String user_email =  request.getParameter("user_email");
		
		
		//System.out.println(user_id + " " + user_name + " " + user_tele);
		int u_id = Integer.valueOf(user_id);
		
		//密码MD5加密
		String password = MD5.md5(user_pwd);
		
		//获取时间
		DealDate dd = new DealDate();
		String UpdateTime = dd.getDate();
		//System.out.println("time = " + UpdateTime);
		boolean flag = false;
		
		//查询用户
		SchoolUserDAO ud = new SchoolUserDAOImpl();
		SchoolUser user = ud.query(u_id);
		//System.out.println(user.toString());
		//System.out.println(user.toString());
		user.setUserUpdateTime(UpdateTime);
		user.setUserPassword(password);
		user.setUserName(user_name);
		user.setUserPhone(user_tele);
		user.setUserEmail(user_email);
		user.setUserPasswords(user_pwd);
		
		//更新用户
		flag = ud.update(user);
		//System.out.println(flag);
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		if(flag) {
			request.getSession().setAttribute("susername", user_name);
			request.getSession().setAttribute("susertele", user_tele);
			request.getSession().setAttribute("suseremail", user_email);
			request.getSession().setAttribute("suserid", u_id);
			request.getSession().setAttribute("password", user_pwd);
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
	
	public String DeleteSchoolUser() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_id =  request.getParameter("user_id");
		
		int u_id = Integer.valueOf(user_id);
		
		
		boolean flag = false;
		
		//查询用户
		SchoolUserDAO ud = new SchoolUserDAOImpl();
		SchoolUser user = ud.query(u_id);
		
		
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
			return null;
		}
		
	}
	
	public String AccountIsExit() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_account =  request.getParameter("user_account");
		//System.out.println(user_account);
		SchoolUserDAO ud = new SchoolUserDAOImpl();
		List<SchoolUser> list = ud.QueryByAccount(user_account);
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		//System.out.println(list.size());
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
		SchoolUserDAO ud = new SchoolUserDAOImpl();
		SchoolUser user = ud.query(u_id);
		
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
			return null;
		}
	}
	
	
	public String UserToEdu() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_id =  request.getParameter("user_id");
		
		int u_id = Integer.valueOf(user_id);
		
		
		boolean flag = false;
		
		//查询用户
		SchoolUserDAO ud = new SchoolUserDAOImpl();
		SchoolUser user = ud.query(u_id);
		
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
			return null;
		}
	}
}
