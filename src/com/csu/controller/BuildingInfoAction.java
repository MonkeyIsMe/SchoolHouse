package com.csu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csu.dao.BuildingDAO;
import com.csu.dao.impl.BuildingDAOImpl;
import com.csu.model.Building;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BuildingInfoAction extends ActionSupport{
	
	public String QueryByBuildingId() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String building_id =  request.getParameter("building_id");
		
		int b_id = Integer.valueOf(building_id);
		
		BuildingDAO bd = new BuildingDAOImpl();
		Building building = bd.query(b_id);
		
		JSONObject jo = JSONObject.fromObject(building);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(jo.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryAllBuilding() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		BuildingDAO bd = new BuildingDAOImpl();
		List<Building> list = bd.getAllBuilding();
		
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
