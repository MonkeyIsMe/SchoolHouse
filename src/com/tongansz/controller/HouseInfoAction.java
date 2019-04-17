package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.BuildingDAO;
import com.tongansz.dao.HouseDAO;
import com.tongansz.dao.impl.BuildingDAOImpl;
import com.tongansz.dao.impl.HouseDAOImpl;
import com.tongansz.model.Building;
import com.tongansz.model.House;

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
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(username == "" || username == null) 
			return null;
		
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
	
	public String QueryHouseBySchoolId() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String school_id =  request.getParameter("school_id");
		int sid = Integer.valueOf(school_id);
		HouseDAO hd = new HouseDAOImpl();
		BuildingDAO bd = new BuildingDAOImpl();
		List<Building> list = bd.getBuildingBySchoolId(sid);
		JSONArray arr = new JSONArray();
		for(Building building : list) {
			if(building.getBuildingFlag() == 1) {
				
				int bid = building.getBuildingId();
				List<House> houselist = hd.getHouseByBuilding(bid);
				for(House house : houselist) {
					JSONObject jo = JSONObject.fromObject(house);
					arr.add(jo);
				}
				
			}
			 
		}
		
/*		HouseDAO hd = new HouseDAOImpl();
		List<House> list = hd.getAllHouse();
		
		JSONArray arr = JSONArray.fromObject(list);*/
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryHouseByBuildingId() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String area_id =  request.getParameter("area_id");
		int a_id = Integer.valueOf(area_id);
		JSONArray ja = new JSONArray();
		BuildingDAO bd = new BuildingDAOImpl();
		List<Building> blist = bd.getBuildingByAreaId(a_id);
		for(Building building : blist) {
			int bid = building.getBuildingId();
			HouseDAO hd = new HouseDAOImpl();
			List<House> hlist = hd.getHouseByBuilding(bid);
			for(House house : hlist) {
				JSONObject jo = JSONObject.fromObject(house);
				ja.add(jo);
			}
		}
		
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(ja.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
}
