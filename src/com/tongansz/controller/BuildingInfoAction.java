package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.BuildingDAO;
import com.tongansz.dao.impl.BuildingDAOImpl;
import com.tongansz.model.Building;

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
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(username == "" || username == null) 
			return null;
		
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
	
	public String QueryBuildingByFlag() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		BuildingDAO bd = new BuildingDAOImpl();
		List<Building> list = bd.getAllBuilding();
		JSONArray arr = new JSONArray();
		for(Building building : list) {
			if(building.getBuildingFlag() == 1) {
				JSONObject jo = JSONObject.fromObject(building);
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
	
	public String QueryBuildingBySchoolIdFlag() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		
		HttpServletRequest request= ServletActionContext.getRequest();
		String school_id =  request.getParameter("school_id");
		int sid = Integer.valueOf(school_id);
		
		BuildingDAO bd = new BuildingDAOImpl();
		List<Building> list = bd.getBuildingBySchoolId(sid);
		JSONArray arr = new JSONArray();
		for(Building building : list) {
				JSONObject jo = JSONObject.fromObject(building);
				arr.add(jo); 
		}
		
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryBuildingInvaild() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		BuildingDAO bd = new BuildingDAOImpl();
		List<Building> list = bd.getAllBuilding();
		JSONArray arr = new JSONArray();
		for(Building building : list) {
			if(building.getBuildingFlag() !=0 ) {
				JSONObject jo = JSONObject.fromObject(building);
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
	
	
	public String QueryBuildingBySchoolId() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String school_id =  request.getParameter("school_id");
		System.out.println(school_id);
		BuildingDAO bd = new BuildingDAOImpl();
		int sid = Integer.valueOf(school_id);
		List<Building> list = bd.getBuildingBySchoolId(sid);
		PrintWriter out = null;
		JSONArray arr = JSONArray.fromObject(list);
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryBuildingByAreaId() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String area_id =  request.getParameter("area_id");
		//System.out.println(school_id);
		BuildingDAO bd = new BuildingDAOImpl();
		int aid = Integer.valueOf(area_id);
		List<Building> list = bd.getBuildingByAreaId(aid);
		PrintWriter out = null;
		JSONArray arr = JSONArray.fromObject(list);
		out = ServletActionContext.getResponse().getWriter();
		out.println(arr.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
}
