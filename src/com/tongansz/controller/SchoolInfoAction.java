package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.SchoolDAO;
import com.tongansz.dao.impl.SchoolDAOImpl;
import com.tongansz.model.School;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SchoolInfoAction extends ActionSupport{

	public String QueryBySchoolId() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String school_id =  request.getParameter("school_id");
		int s_id = Integer.valueOf(school_id);
		
		SchoolDAO sd = new SchoolDAOImpl();
		School school = sd.query(s_id);
		
		JSONObject jo = JSONObject.fromObject(school);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(jo.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
		
	}
	
	public String QueryAllSchool() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(username == "" || username == null) 
			return null;
		
		SchoolDAO sd = new SchoolDAOImpl();
		List<School> list =sd.getAllSchool();
		
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
