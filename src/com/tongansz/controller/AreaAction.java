package com.tongansz.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.AreaDAO;
import com.tongansz.dao.SchoolDAO;
import com.tongansz.dao.impl.AreaDAOImpl;
import com.tongansz.dao.impl.SchoolDAOImpl;
import com.tongansz.model.Area;
import com.tongansz.model.School;
import com.tongansz.utils.DealDate;

public class AreaAction extends ActionSupport{


	public String AddArea() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String area_name = request.getParameter("area_name");
		
		
		DealDate dd = new DealDate();
		String CreatTime = dd.getDate();
		
		AreaDAO ad = new AreaDAOImpl();
		Area area = new Area();
		area.setAreaCreatTime(CreatTime);
		area.setAreaName(area_name);
		
		boolean flag = false;
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		flag = ad.add(area);
		
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
	
	
	public String UpdateArea() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String area_id = request.getParameter("area_id");
		String area_name = request.getParameter("area_name");
		
		int a_id = Integer.valueOf(area_id);
		
		AreaDAO ad = new AreaDAOImpl();
		Area area = ad.query(a_id);
		area.setAreaName(area_name);
		
		boolean flag = false;
		
		SchoolDAO sd = new SchoolDAOImpl();
		List<School> list =  sd.getSchoolByArea(a_id);
		for(School school : list) {
			school.setAreaName(area_name);
			sd.update(school);
		}
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		flag = ad.update(area);
		ad.update(area);
		
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
	
	
	public String DeleteArea() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String area_id = request.getParameter("area_id");
		
		int a_id = Integer.valueOf(area_id);
		
		
		System.out.println(a_id);
		AreaDAO ad = new AreaDAOImpl();
		Area area = ad.query(a_id);
		
		boolean flag = false;
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		flag = ad.delete(area);
		
		if(ad.query(a_id) != null) ad.delete(ad.query(a_id));
		
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
	
	public String AreaIsExit() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String area_id = request.getParameter("area_id");
		
		int a_id = Integer.valueOf(area_id);
		
		boolean flag = true;
		AreaDAO ad = new AreaDAOImpl();
		Area area = ad.query(a_id);
		if(area == null) flag = false;
		
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
	
	public String AreaByName() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String area_name = request.getParameter("area_name");
		
		
		boolean flag = false;
		AreaDAO ad = new AreaDAOImpl();
		List<Area> list = ad.GetByName(area_name);
		
		if(list.size() == 0) flag = true;
		
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
