package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.SchoolUserDAO;
import com.tongansz.dao.impl.SchoolUserDAOImpl;
import com.tongansz.model.SchoolUser;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SchoolUserInfoAction extends ActionSupport {
	
	public String QueryBySchoolUesrId() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_id =  request.getParameter("user_id");
		
		SchoolUserDAO ud =  new SchoolUserDAOImpl();
		int u_id = Integer.valueOf(user_id);
		SchoolUser user = ud.query(u_id); 
		
		JSONObject jo = JSONObject.fromObject(user);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(jo.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryAllSchoolUser() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		SchoolUserDAO ud =  new SchoolUserDAOImpl();
		List<SchoolUser> list = ud.getAllUser();
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
	
	
	public String QueryAllSchoolUserByPageSize() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String Rows =  request.getParameter("Rows");
		String PageSize =  request.getParameter("PageSize");
		int rows = Integer.valueOf(Rows);
		int pagesize = Integer.valueOf(PageSize);
		
		SchoolUserDAO ud =  new SchoolUserDAOImpl();
		List<SchoolUser> list = ud.getAllUserByPageSize(rows, pagesize);
		
		JSONArray arr = JSONArray.fromObject(list);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QuerySchoolUserByAccount() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_account =  request.getParameter("user_account");
		
		SchoolUserDAO ud =  new SchoolUserDAOImpl();
		List<SchoolUser> list = ud.QueryByAccount(user_account);
		
		JSONArray arr = JSONArray.fromObject(list);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QuerySchoolUserByFlag() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_flag =  request.getParameter("user_flag");
		int uflag = Integer.valueOf(user_flag);
		
		SchoolUserDAO ud =  new SchoolUserDAOImpl();
		List<SchoolUser> list = ud.QueryByFlag(uflag);
		
		JSONArray arr = JSONArray.fromObject(list);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QuerySchoolUserByFlagByPageSize() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_flag =  request.getParameter("user_flag");
		String Rows =  request.getParameter("Rows");
		String PageSize =  request.getParameter("PageSize");
		int uflag = Integer.valueOf(user_flag);
		int rows = Integer.valueOf(Rows);
		int pagesize = Integer.valueOf(PageSize);
		
		SchoolUserDAO ud =  new SchoolUserDAOImpl();
		List<SchoolUser> list = ud.QueryByFlagByPageSize(uflag, rows, pagesize);
		
		JSONArray arr = JSONArray.fromObject(list);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String SchoolAccountIsExit() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String user_account =  request.getParameter("user_account");
		System.out.println(user_account);
		SchoolUserDAO ud = new SchoolUserDAOImpl();
		List<SchoolUser> list = ud.QueryByAccount(user_account);
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
	
}
