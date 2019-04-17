package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.SchoolUserDAO;
import com.tongansz.dao.UserDAO;
import com.tongansz.dao.impl.SchoolUserDAOImpl;
import com.tongansz.dao.impl.UserDAOImpl;
import com.tongansz.model.SchoolUser;
import com.tongansz.model.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserInfoAction extends ActionSupport {
	
	public String QueryByUesrId() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_id =  request.getParameter("user_id");
		
		UserDAO ud =  new UserDAOImpl();
		int u_id = Integer.valueOf(user_id);
		User user = ud.query(u_id); 
		
		JSONObject jo = JSONObject.fromObject(user);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(jo.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryAllUser() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(username == "" || username == null) 
			return null;
		
		UserDAO ud =  new UserDAOImpl();
		List<User> list = ud.getAllUser();
		//System.out.println(list.size());
		JSONArray arr = JSONArray.fromObject(list);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QuerySchoolManager() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		SchoolUserDAO ud =  new SchoolUserDAOImpl();
		List<SchoolUser> list = ud.getAllUser();
		//System.out.println(list.size());
		JSONArray ja = new JSONArray();
		for(SchoolUser user : list) {
			if(user.getUserFlag() == 0) {
				JSONObject jo = JSONObject.fromObject(user);
				ja.add(jo);
			}
		}
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(ja.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryAllUserByPageSize() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String Rows =  request.getParameter("Rows");
		String PageSize =  request.getParameter("PageSize");
		int rows = Integer.valueOf(Rows);
		int pagesize = Integer.valueOf(PageSize);
		
		UserDAO ud =  new UserDAOImpl();
		List<User> list = ud.getAllUserByPageSize(rows, pagesize);
		
		JSONArray arr = JSONArray.fromObject(list);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryByAccount() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_account =  request.getParameter("user_account");
		
		UserDAO ud =  new UserDAOImpl();
		List<User> list = ud.QueryByAccount(user_account);
		
		JSONArray arr = JSONArray.fromObject(list);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryByFlag() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_flag =  request.getParameter("user_flag");
		int uflag = Integer.valueOf(user_flag);
		
		UserDAO ud =  new UserDAOImpl();
		List<User> list = ud.QueryByFlag(uflag);
		
		JSONArray arr = JSONArray.fromObject(list);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryByFlagByPageSize() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_flag =  request.getParameter("user_flag");
		String Rows =  request.getParameter("Rows");
		String PageSize =  request.getParameter("PageSize");
		int uflag = Integer.valueOf(user_flag);
		int rows = Integer.valueOf(Rows);
		int pagesize = Integer.valueOf(PageSize);
		
		UserDAO ud =  new UserDAOImpl();
		List<User> list = ud.QueryByFlagByPageSize(uflag, rows, pagesize);
		
		JSONArray arr = JSONArray.fromObject(list);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	

	
}
