package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.AreaDAO;
import com.tongansz.dao.BuildingDAO;
import com.tongansz.dao.HouseDAO;
import com.tongansz.dao.SchoolDAO;
import com.tongansz.dao.impl.AreaDAOImpl;
import com.tongansz.dao.impl.BuildingDAOImpl;
import com.tongansz.dao.impl.HouseDAOImpl;
import com.tongansz.dao.impl.SchoolDAOImpl;
import com.tongansz.model.Area;
import com.tongansz.model.Building;
import com.tongansz.model.House;
import com.tongansz.model.School;
import com.tongansz.utils.DealDate;

public class BuildingAction extends ActionSupport{
	
	public String AddBuilding() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String building_name =  request.getParameter("building_name");
		String building_address =  request.getParameter("building_address");
		String building_street =  request.getParameter("building_street");
		String school_id = request.getParameter("school_id");
		String area_id = request.getParameter("area_id");
		
		
		int aid = Integer.valueOf(area_id);
		int sid = Integer.valueOf(school_id);
		SchoolDAO sd = new SchoolDAOImpl();
		School school = sd.query(sid);
		
		DealDate dd = new DealDate();
		String CreatTime = dd.getDate();
		
		BuildingDAO bd = new BuildingDAOImpl();
		Building building = new Building();
		
		building.setAreaId(aid);
		building.setSchoolName(school.getSchoolName());
		building.setBuildingAddress(building_address);
		building.setBuildingName(building_name);
		building.setBuildingStreet(building_street);
		building.setBuildingCreatTime(CreatTime);
		building.setSchoolId(sid);
		building.setBuildingFlag(0);
		building.setBuildingHint("未提交");
		
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
				return null;
		}
	}
	
	public String UpdateBuilding() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String building_id =  request.getParameter("building_id");
		String building_name =  request.getParameter("building_name");
		String building_address =  request.getParameter("building_address");
		String building_street =  request.getParameter("building_street");
		//System.out.println(building_id);
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
				return null;
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
		
		HouseDAO hd = new HouseDAOImpl();
		List<House> list = hd.getHouseByBuilding(b_id);
		for(House house : list) {
			hd.delete(house);
		}
		
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
				return null;
		}
	}
	
	public String SetBuildingValid() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String building_id =  request.getParameter("building_id");
		
		int b_id = Integer.valueOf(building_id);
		BuildingDAO bd = new BuildingDAOImpl();
		Building building = bd.query(b_id);
		building.setBuildingFlag(1);
		building.setBuildingHint("审核通过");
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
				return null;
		}
	}
	
	public String SubmitBuilding() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String building_id =  request.getParameter("building_id");
		
		int b_id = Integer.valueOf(building_id);
		BuildingDAO bd = new BuildingDAOImpl();
		Building building = bd.query(b_id);
		building.setBuildingFlag(2);
		building.setBuildingHint("审核中");
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
				return null;
		}
	}
	
	public String UnSubmitBuilding() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String building_id =  request.getParameter("building_id");
		
		int b_id = Integer.valueOf(building_id);
		BuildingDAO bd = new BuildingDAOImpl();
		Building building = bd.query(b_id);
		building.setBuildingFlag(-1);
		building.setBuildingHint("未通过");
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
				return null;
		}
	}
	
	public String BuildingIsExit() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String building_id =  request.getParameter("building_id");
		
		int b_id = Integer.valueOf(building_id);
		BuildingDAO bd = new BuildingDAOImpl();
		Building building = bd.query(b_id);
		
		boolean flag = false;
		
		if(building != null) flag = true;
		
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
	
	public String BuildingIsAlready() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String building_name =  request.getParameter("building_name");
		String area_id =  request.getParameter("area_id");
		
		int aid = Integer.valueOf(area_id);
		BuildingDAO bd = new BuildingDAOImpl();
		
		boolean flag = true;
		
		List<Building> list = bd.getBuildingByAreaId(aid);
		if(list.size() == 0) flag = true;
		else {
			for(Building building : list) {
				String bname = building.getBuildingName();
				if(bname.equals(building_name)) flag = false;
			}
		}
		
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
	
	public String BuildingAreaIsExit() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String building_id =  request.getParameter("building_id");
		String area_id =  request.getParameter("area_id");
		
		int a_id = Integer.valueOf(area_id);
		int b_id = Integer.valueOf(building_id);
		BuildingDAO bd = new BuildingDAOImpl();
		Building building = bd.query(b_id);
		AreaDAO ad = new AreaDAOImpl();
		Area area = ad.query(a_id);
		
		boolean aflag = false;
		boolean flag = false;
		
		if(building != null) flag = true;
		if(area != null) aflag = true;
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
				
		if(flag && aflag) {
			out.println("Success");
			out.flush(); 
			out.close(); 
			return null;
		}
		else if(!flag && aflag) {
			out.println("NoBuilding");
			out.flush(); 
			out.close(); 
			return null;
		}
		else if(flag && !aflag) {
			out.println("NoArea");
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
