package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.StudentDAO;
import com.tongansz.dao.impl.StudentDAOImpl;
import com.tongansz.model.Student;

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
	
	
	public String QueryStudentByPageSize() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String Rows =  request.getParameter("rows");
		String PageSize =  request.getParameter("pagesize");
		
		int rows = Integer.valueOf(Rows);
		int pagesize = Integer.valueOf(PageSize);
		
		StudentDAO sd = new StudentDAOImpl();
		List<Student> list = sd.getAllStudentByPageSize(rows, pagesize);
		
		JSONArray arr = JSONArray.fromObject(list);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryStudentBySchool() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String School_Name =  request.getParameter("School_Name");
		
		
		StudentDAO sd = new StudentDAOImpl();
		List<Student> list = sd.getStudentBySchool(School_Name);
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
	
	public String QueryStudentBySchoolPageSize() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String School_Name =  request.getParameter("School_Name");
		String rows =  request.getParameter("rowss");
		String pagesize =  request.getParameter("pagesize");
		System.out.println(School_Name + " " + rows + " " + pagesize);
		
		int row = Integer.valueOf(rows);
		int page = Integer.valueOf(pagesize);
		
		StudentDAO sd = new StudentDAOImpl();
		List<Student> list = sd.getStudentBySchoolPageSize(School_Name, row, page);
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
	
	public String QueryStudentBySchoolValid() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String School_Name =  request.getParameter("School_Name");
		
		
		StudentDAO sd = new StudentDAOImpl();
		JSONArray arr = new JSONArray();
		List<Student> list = sd.getStudentBySchool(School_Name);
		for(Student stu : list) {
			int IsValid = stu.getStudentValid();
			if(IsValid == 1) {
				JSONObject jo = JSONObject.fromObject(stu);
				arr.add(jo);
			}
		}
		
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryStudentBySchoolInValid() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String School_Name =  request.getParameter("School_Name");
		
		
		StudentDAO sd = new StudentDAOImpl();
		JSONArray arr = new JSONArray();
		List<Student> list = sd.getStudentBySchool(School_Name);
		for(Student stu : list) {
			int IsValid = stu.getStudentValid();
			if(IsValid == 0) {
				JSONObject jo = JSONObject.fromObject(stu);
				arr.add(jo);
			}
		}
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
}
