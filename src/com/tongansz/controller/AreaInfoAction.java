package com.tongansz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongansz.dao.AreaDAO;
import com.tongansz.dao.impl.AreaDAOImpl;
import com.tongansz.model.Area;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AreaInfoAction extends ActionSupport{
	
	public String QueryByAreaId() throws IOException{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String area_id =  request.getParameter("area_id");
		int a_id = Integer.valueOf(area_id);
		
		AreaDAO ad = new AreaDAOImpl();
		Area area = ad.query(a_id);
		
		JSONObject jo = JSONObject.fromObject(area);
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		out.println(jo.toString());
        out.flush(); 
        out.close(); 
		//System.out.println(arr.size());
		return SUCCESS;
	}
	
	public String QueryAllArea() throws IOException{

		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(username == "" || username == null) 
			return null;
		JSONArray arr = new JSONArray();
		AreaDAO ad = new AreaDAOImpl();
		List<Area> list = ad.getAllArea();
		int j = 1;
		for(Area area : list) {
			JSONObject jo = JSONObject.fromObject(area);
			jo.accumulate("id", j++);
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
}
