package com.csu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csu.dao.HouseDAO;
import com.csu.dao.impl.HouseDAOImpl;
import com.csu.model.House;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HouseInfoAction extends ActionSupport{
	
	public String QueryByHouseId() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String house_id =  request.getParameter("house_id");
		
		int h_id = Integer.valueOf(house_id);
		
		HouseDAO hd = new HouseDAOImpl();
		House house = hd.query(h_id);
		
		JSONObject jo = JSONObject.fromObject(house);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(jo.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryAllHouse() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		HouseDAO hd = new HouseDAOImpl();
		List<House> list = hd.getAllHouse();
		
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
