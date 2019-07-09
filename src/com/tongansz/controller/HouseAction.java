package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.BuildingDAO;
import com.tongansz.dao.HouseDAO;
import com.tongansz.dao.impl.BuildingDAOImpl;
import com.tongansz.dao.impl.HouseDAOImpl;
import com.tongansz.model.Building;
import com.tongansz.model.House;
import com.tongansz.utils.DealDate;

public class HouseAction extends ActionSupport{
	
	public String AddHouse() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String buding_id =  request.getParameter("buding_id");
		String house_name =  request.getParameter("house_name");
		String house_room =  request.getParameter("house_room");
		
		
		int b_id = Integer.valueOf(buding_id);
		
		DealDate dd = new DealDate();
		String CreatTime = dd.getDate();
		
		HouseDAO hd = new HouseDAOImpl();
		House house = new House();
		
		BuildingDAO bd = new BuildingDAOImpl();
		Building building = bd.query(b_id);
		
		house.setBuildingId(b_id);
		house.setHouseName(house_name);
		house.setHouseRoom(house_room);
		house.setBuildingName(building.getBuildingName());
		house.setHouseCreatTime(CreatTime);
		
		boolean flag = false;
		
		flag = hd.add(house);
		
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
	
	public String UpdateHouse() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String house_id =  request.getParameter("house_id");
		String buding_id =  request.getParameter("buding_id");
		String house_name =  request.getParameter("house_name");
		String house_room =  request.getParameter("house_room");
		
		
		int b_id = Integer.valueOf(buding_id);
		int h_id = Integer.valueOf(house_id);
		
		HouseDAO hd = new HouseDAOImpl();
		House house = hd.query(h_id);
		
		BuildingDAO bd = new BuildingDAOImpl();
		Building building = bd.query(b_id);
		
		house.setBuildingId(b_id);
		house.setHouseName(house_name);
		house.setBuildingName(building.getBuildingName());
		house.setHouseRoom(house_room);
		
		boolean flag = false;
		
		flag = hd.update(house);
		
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

	public String DeteleHouse() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String house_id =  request.getParameter("house_id");
		int h_id = Integer.valueOf(house_id);
		
		HouseDAO hd = new HouseDAOImpl();
		House house = hd.query(h_id);
		boolean flag = false;

		flag = hd.delete(house);
		
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
	
	
	public String HouseIsExit() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String house_name =  request.getParameter("house_name");
		String house_room =  request.getParameter("house_room");
		String building_id = request.getParameter("building_id");
		
		
		boolean flag = false;
		
		HouseDAO hd = new HouseDAOImpl();
		List<House> list = hd.getHouseByName(building_id, house_name);
		
		if(list.size() ==0) {
			flag = true;
		}

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
