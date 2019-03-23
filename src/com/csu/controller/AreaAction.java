package com.csu.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csu.dao.AreaDAO;
import com.csu.dao.impl.AreaDAOImpl;
import com.csu.model.Area;
import com.csu.utils.DealDate;
import com.opensymphony.xwork2.ActionSupport;

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
			return ERROR;
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
		System.out.println(area_name);
		boolean flag = false;
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		flag = ad.update(area);
		
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
		
		
		AreaDAO ad = new AreaDAOImpl();
		Area area = ad.query(a_id);
		
		boolean flag = false;
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		flag = ad.delete(area);
		
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
}
