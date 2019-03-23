package com.csu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csu.dao.AreaDAO;
import com.csu.dao.impl.AreaDAOImpl;
import com.csu.model.Area;
import com.opensymphony.xwork2.ActionSupport;

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
		
		AreaDAO ad = new AreaDAOImpl();
		List<Area> list = ad.getAllArea();
		
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
