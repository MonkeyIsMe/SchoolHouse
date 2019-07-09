package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.TempStudentDAO;
import com.tongansz.dao.impl.TempStudentDAOImpl;
import com.tongansz.model.TempStudent;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TempStudentInfoAction extends ActionSupport{
	
	public String QueryByTempStudentId() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String student_id =  request.getParameter("student_id");
		int stu_id = Integer.valueOf(student_id);
		
		TempStudentDAO sd = new TempStudentDAOImpl();
		TempStudent stu = sd.query(stu_id);
		
		JSONObject jo = JSONObject.fromObject(stu);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(jo.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryAllTempStudent() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();

		HttpSession session = request.getSession();
		
		TempStudentDAO sd = new TempStudentDAOImpl();
		List<TempStudent> list = sd.getAllStudent();
		
		JSONArray arr = JSONArray.fromObject(list);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryTempStudentByPageSize() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String Rows =  request.getParameter("rows");
		String PageSize =  request.getParameter("pagesize");
		
		int rows = Integer.valueOf(Rows);
		int pagesize = Integer.valueOf(PageSize);
		
		TempStudentDAO sd = new TempStudentDAOImpl();
		List<TempStudent> list = sd.getAllStudentByPageSize(rows, pagesize);
		
		JSONArray arr = JSONArray.fromObject(list);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryTempStudentBySchool() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String School_Name =  request.getParameter("School_Name");
		
		
		TempStudentDAO sd = new TempStudentDAOImpl();
		List<TempStudent> list = sd.getStudentBySchool(School_Name);
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
	
	public String QueryTempStudentBySchoolValid() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String School_Name =  request.getParameter("School_Name");
		
		
		TempStudentDAO sd = new TempStudentDAOImpl();
		JSONArray arr = new JSONArray();
		List<TempStudent> list = sd.getStudentBySchool(School_Name);
		for(TempStudent stu : list) {
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
	
	public String QueryTempStudentBySchoolInValid() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String School_Name =  request.getParameter("School_Name");
		
		
		TempStudentDAO sd = new TempStudentDAOImpl();
		JSONArray arr = new JSONArray();
		List<TempStudent> list = sd.getStudentBySchool(School_Name);
		for(TempStudent stu : list) {
			int IsValid = stu.getStudentValid();
			if(IsValid == -1) {
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
	
	
	public String QueryTempStudentBySchoolValidPageSize() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String School_Name =  request.getParameter("School_Name");
		String Rows =  request.getParameter("rows");
		String PageSize =  request.getParameter("pagesize");
		
		int rows = Integer.valueOf(Rows);
		int pagesize = Integer.valueOf(PageSize);
		
		TempStudentDAO sd = new TempStudentDAOImpl();
		JSONArray arr = new JSONArray();
		List<TempStudent> list = sd.getStudentBySchoolPageSize(School_Name, rows, pagesize);
		for(TempStudent stu : list) {
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
	
	public String QueryTempStudentBySchoolInValidPageSize() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String School_Name =  request.getParameter("School_Name");
		String Rows =  request.getParameter("rows");
		String PageSize =  request.getParameter("pagesize");
		
		int rows = Integer.valueOf(Rows);
		int pagesize = Integer.valueOf(PageSize);
		
		TempStudentDAO sd = new TempStudentDAOImpl();
		JSONArray arr = new JSONArray();
		List<TempStudent> list = sd.getStudentBySchoolPageSize(School_Name, rows, pagesize);
		for(TempStudent stu : list) {
			int IsValid = stu.getStudentValid();
			if(IsValid == -1) {
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

	public String QueryTempStudentInvalid() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		TempStudentDAO sd = new TempStudentDAOImpl();
		JSONArray arr = new JSONArray();
		List<TempStudent> list = sd.getAllStudent();
		for(TempStudent stu : list) {
			int IsValid = stu.getStudentValid();
			if(IsValid == -1) {
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
	
	public String QueryTempStudentValid() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		TempStudentDAO sd = new TempStudentDAOImpl();
		JSONArray arr = new JSONArray();
		List<TempStudent> list = sd.getAllStudent();
		for(TempStudent stu : list) {
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
	
	public String QueryTempStudentCheck() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		boolean flag = false;
		
		TempStudentDAO sd = new TempStudentDAOImpl();
		JSONArray arr = new JSONArray();
		List<TempStudent> list = sd.getAllStudent();
		for(TempStudent stu : list) {
			String check = stu.getStudentRoomId();

			if(!(check==null||check.equals(""))){
				System.out.println(stu.toString());
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
