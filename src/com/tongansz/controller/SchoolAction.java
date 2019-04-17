package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.AreaDAO;
import com.tongansz.dao.SchoolDAO;
import com.tongansz.dao.SchoolUserDAO;
import com.tongansz.dao.impl.AreaDAOImpl;
import com.tongansz.dao.impl.SchoolDAOImpl;
import com.tongansz.dao.impl.SchoolUserDAOImpl;
import com.tongansz.model.Area;
import com.tongansz.model.School;
import com.tongansz.model.SchoolUser;
import com.tongansz.utils.DealDate;

public class SchoolAction extends ActionSupport{

	public String AddSchool() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String school_name =  request.getParameter("school_name");
		String school_address =  request.getParameter("school_address");
		String school_street =  request.getParameter("school_street");
		String school_legal =  request.getParameter("school_legal");
		String school_tele =  request.getParameter("school_tele");
		String area_id =  request.getParameter("area_id");
		

		DealDate dd = new DealDate();
		String CreatTime = dd.getDate();
		
		boolean flag = false;
		int a_id = Integer.valueOf(area_id);
		
		AreaDAO ad = new AreaDAOImpl();
		Area area = ad.query(a_id);
		String AreaName = area.getAreaName();
		
		SchoolDAO sd = new SchoolDAOImpl();
		School school = new School();
		
		school.setSchoolName(school_name);
		school.setSchoolAddress(school_address);
		school.setSchoolIegalPerson(school_legal);
		school.setSchoolPhone(school_tele);
		school.setSchoolStreet(school_street);
		school.setAreaId(a_id);
		school.setSchoolCreatTime(CreatTime);
		school.setAreaName(AreaName);
		
		flag = sd.add(school);
		
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
	
	public String UpdateSchool() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String school_id =  request.getParameter("school_id");
		String school_name =  request.getParameter("school_name");
		String school_address =  request.getParameter("school_address");
		String school_street =  request.getParameter("school_street");
		String school_legal =  request.getParameter("school_legal");
		String school_tele =  request.getParameter("school_tele");
		String area_id =  request.getParameter("area_id");
		
		int s_id = Integer.valueOf(school_id);
		int a_id = Integer.valueOf(area_id);
		
		
		AreaDAO ad = new AreaDAOImpl();
		Area area = ad.query(a_id);
		
		SchoolDAO sd = new SchoolDAOImpl();
		School school = sd.query(s_id);
		school.setSchoolName(school_name);
		school.setSchoolAddress(school_address);
		school.setSchoolIegalPerson(school_legal);
		school.setSchoolPhone(school_tele);
		school.setSchoolStreet(school_street);
		school.setAreaId(a_id);
		school.setAreaName(area.getAreaName());
		
		SchoolUserDAO sud = new SchoolUserDAOImpl();
		List<SchoolUser> list = sud.getAllUserBySchoolId(s_id);
		for(SchoolUser su : list) {
			su.setSchoolName(school_name);
			sud.update(su);
		}
		
		boolean flag = false;
		
		flag = sd.update(school);
		
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

	public String DeleteSchool() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String school_id =  request.getParameter("school_id");
		
		int s_id = Integer.valueOf(school_id);
		
		SchoolDAO sd = new SchoolDAOImpl();
		School school = sd.query(s_id);
		
		SchoolUserDAO sud = new SchoolUserDAOImpl();
		List<SchoolUser> sulist = sud.getAllUserBySchoolId(s_id);
		for(SchoolUser su : sulist) {
			sud.delete(su);
		}
		
		boolean flag = false;
		
		flag = sd.delete(school);
		
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
	
	public String IsSchool() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String school_id =  request.getParameter("school_id");
		//System.out.println("school_id = " + school_id);
		int s_id = Integer.valueOf(school_id);
		
		SchoolDAO sd = new SchoolDAOImpl();
		School school = sd.query(s_id);
		
		boolean flag = false;
		
		if(school != null) flag = true;
		
		//返回结果
		PrintWriter out = null;	
		out = ServletActionContext.getResponse().getWriter();
		//System.out.println("flag = " + flag);
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
	
	public String SchoolAlready() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String school_name =  request.getParameter("school_name");
		
		
		SchoolDAO sd = new SchoolDAOImpl();
		
		List<School> list = sd.getSchoolByName(school_name);
		
		boolean flag = false;
		
		//返回结果
		PrintWriter out = null;	
		out = ServletActionContext.getResponse().getWriter();
		if(list.size() == 0 ) {
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
	
	public String ApplyAreaForSchool() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String school_id =  request.getParameter("school_id");
		String are_id =  request.getParameter("are_id");
		
		int sid = Integer.valueOf(school_id);
		int aid = Integer.valueOf(are_id);
		
		SchoolDAO sd = new SchoolDAOImpl();
		School school = sd.query(sid);
		
		AreaDAO ad = new AreaDAOImpl();
		Area area = ad.query(aid);
		
		String AreaName = area.getAreaName();
		
		boolean flag = false;
		
		school.setAreaName(AreaName);
		
		flag = sd.update(school);
		
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
