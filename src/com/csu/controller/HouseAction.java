package com.csu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csu.dao.HouseDAO;
import com.csu.dao.impl.HouseDAOImpl;
import com.csu.model.House;
import com.csu.utils.DealDate;
import com.opensymphony.xwork2.ActionSupport;

public class HouseAction extends ActionSupport{
	
	public String AddHouse() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String buding_id =  request.getParameter("buding_id");
		String house_name =  request.getParameter("house_name");
		String house_unit =  request.getParameter("house_unit");
		String house_room =  request.getParameter("house_room");
		
		int b_id = Integer.valueOf(buding_id);
		
		DealDate dd = new DealDate();
		String CreatTime = dd.getDate();
		
		HouseDAO hd = new HouseDAOImpl();
		House house = new House();
		house.setBuildingId(b_id);
		house.setHouseName(house_name);
		house.setHouseUnit(house_unit);
		house.setHouseRoom(house_room);
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
			return ERROR;
				
		}
	}
	
	public String UpdateHouse() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String house_id =  request.getParameter("house_id");
		String buding_id =  request.getParameter("buding_id");
		String house_name =  request.getParameter("house_name");
		String house_unit =  request.getParameter("house_unit");
		String house_room =  request.getParameter("house_room");
		
		int b_id = Integer.valueOf(buding_id);
		int h_id = Integer.valueOf(house_id);
		
		HouseDAO hd = new HouseDAOImpl();
		House house = hd.query(h_id);
		
		house.setBuildingId(b_id);
		house.setHouseName(house_name);
		house.setHouseUnit(house_unit);
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
			return ERROR;
				
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
			return ERROR;
				
		}
	}
}
