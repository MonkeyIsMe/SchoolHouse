package com.csu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csu.dao.StudentDAO;
import com.csu.dao.impl.StudentDAOImpl;
import com.csu.model.Student;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentInfoAction extends ActionSupport{
	
	public String QueryByStudentId() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String student_id =  request.getParameter("student_id");
		int stu_id = Integer.valueOf(student_id);
		
		StudentDAO sd = new StudentDAOImpl();
		Student stu = sd.query(stu_id);
		
		JSONObject jo = JSONObject.fromObject(stu);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(jo.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryAllStudent() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();

		StudentDAO sd = new StudentDAOImpl();
		List<Student> list = sd.getAllStudent();
		
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
