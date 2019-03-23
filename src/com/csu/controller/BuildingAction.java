package com.csu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csu.dao.BuildingDAO;
import com.csu.dao.impl.BuildingDAOImpl;
import com.csu.model.Building;
import com.csu.utils.DealDate;
import com.opensymphony.xwork2.ActionSupport;

public class BuildingAction extends ActionSupport{
	
	public String AddBuilding() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String building_name =  request.getParameter("building_name");
		String building_address =  request.getParameter("building_address");
		String building_street =  request.getParameter("building_street");
		
		DealDate dd = new DealDate();
		String CreatTime = dd.getDate();
		
		BuildingDAO bd = new BuildingDAOImpl();
		Building building = new Building();
		
		building.setBuildingAddress(building_address);
		building.setBuildingName(building_name);
		building.setBuildingStreet(building_street);
		building.setBuildingCreatTime(CreatTime);
		
		boolean flag = false;
		
		flag = bd.add(building);
		
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
				return ERROR;
		}
	}
	
	public String UpdateBuilding() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String building_id =  request.getParameter("building_id");
		String building_name =  request.getParameter("building_name");
		String building_address =  request.getParameter("building_address");
		String building_street =  request.getParameter("building_street");
		
		int b_id = Integer.valueOf(building_id);
		BuildingDAO bd = new BuildingDAOImpl();
		Building building = bd.query(b_id);
		
		building.setBuildingAddress(building_address);
		building.setBuildingName(building_name);
		building.setBuildingStreet(building_street);
		
		boolean flag = false;
		flag = bd.update(building);
		
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
				return ERROR;
		}
	}


	public String DeleteBuilding() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String building_id =  request.getParameter("building_id");
		
		int b_id = Integer.valueOf(building_id);
		BuildingDAO bd = new BuildingDAOImpl();
		Building building = bd.query(b_id);
		
		boolean flag = false;
		
		flag = bd.delete(building);
		
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
				return ERROR;
		}
	}
}
